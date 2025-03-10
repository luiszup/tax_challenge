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
import java.util.List;
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
        registerUserDTO.setUsername("usuarioteste");
        registerUserDTO.setPassword("senha123");
        registerUserDTO.setRoles(Set.of(Roles.USER));

        when(userRepository.existsByUsername(registerUserDTO.getUsername())).thenReturn(false);
        when(passwordEncoder.encode(registerUserDTO.getPassword())).thenReturn("senhacriptografada");

        Set<Role> roles = new HashSet<>();
        roles.add(new Role(Roles.USER.name()));
        when(roleRepository.saveAll(anySet())).thenReturn(new ArrayList<>(roles));

        User savedUser = new User();
        savedUser.setUsername("usuarioteste");
        savedUser.setPassword("senhacriptografada");
        savedUser.setRoles(roles);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.registerUser(registerUserDTO);

        assertNotNull(result);
        assertEquals("usuarioteste", result.getUsername());
        assertEquals("senhacriptografada", result.getPassword());
        assertEquals(1, result.getRoles().size());
        assertTrue(result.getRoles().stream().anyMatch(role -> role.getName().equals("USER")));

        verify(userRepository, times(1)).existsByUsername(registerUserDTO.getUsername());
        verify(passwordEncoder, times(1)).encode(registerUserDTO.getPassword());
        verify(roleRepository, times(1)).saveAll(anySet());
        verify(userRepository, times(1)).save(any(User.class));
    }
}