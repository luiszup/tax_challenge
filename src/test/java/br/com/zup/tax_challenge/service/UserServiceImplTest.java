package br.com.zup.tax_challenge.service;

import br.com.zup.tax_challenge.dto.RegisterUserDTO;
import br.com.zup.tax_challenge.dto.Roles;
import br.com.zup.tax_challenge.model.Role;
import br.com.zup.tax_challenge.model.User;
import br.com.zup.tax_challenge.repository.RoleRepository;
import br.com.zup.tax_challenge.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUserSuccess() {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        registerUserDTO.setUsuario("usuarioteste");
        registerUserDTO.setSenha("senha123");
        registerUserDTO.setCargos(Set.of(Roles.USER));

        when(userRepository.existsByUsername(registerUserDTO.getUsuario())).thenReturn(false);
        when(passwordEncoder.encode(registerUserDTO.getSenha())).thenReturn("senhacriptografada");

        Set<Role> roles = new HashSet<>();
        roles.add(new Role(Roles.USER.name()));
        when(roleRepository.saveAll(anySet())).thenReturn(new ArrayList<>(roles));

        User savedUser = new User();
        savedUser.setUsuario("usuarioteste");
        savedUser.setSenha("senhacriptografada");
        savedUser.setCargos(roles);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.registerUser(registerUserDTO);

        assertNotNull(result);
        assertEquals("usuarioteste", result.getUsuario());
        assertEquals("senhacriptografada", result.getSenha());
        assertEquals(1, result.getCargos().size());
        assertTrue(result.getCargos().stream().anyMatch(role -> role.getName().equals("USER")));

        verify(userRepository, times(1)).existsByUsername(registerUserDTO.getUsuario());
        verify(passwordEncoder, times(1)).encode(registerUserDTO.getSenha());
        verify(roleRepository, times(1)).saveAll(anySet());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void userExistsFail() {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        registerUserDTO.setUsuario("usuarioteste");
        registerUserDTO.setSenha("senha123");
        registerUserDTO.setCargos(Set.of(Roles.USER));

        when(userRepository.existsByUsername(registerUserDTO.getUsuario())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(registerUserDTO);
        });

        assertEquals("O usuário já existe", exception.getMessage());

        verify(userRepository, times(1)).existsByUsername(registerUserDTO.getUsuario());
        verifyNoInteractions(passwordEncoder);
        verifyNoInteractions(roleRepository);
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    void registerUserFail() {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        registerUserDTO.setUsuario("usuarioteste");
        registerUserDTO.setSenha("senha123");
        registerUserDTO.setCargos(Set.of(Roles.USER));

        when(userRepository.existsByUsername(registerUserDTO.getUsuario())).thenReturn(false);
        when(passwordEncoder.encode(registerUserDTO.getSenha())).thenReturn("senhacriptografada");
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("Erro ao salvar no banco de dados"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(registerUserDTO);
        });

        assertEquals("Erro ao salvar no banco de dados", exception.getMessage());

        verify(userRepository, times(1)).existsByUsername(registerUserDTO.getUsuario());
        verify(passwordEncoder, times(1)).encode(registerUserDTO.getSenha());
        verify(userRepository, times(1)).save(any(User.class));
    }
}