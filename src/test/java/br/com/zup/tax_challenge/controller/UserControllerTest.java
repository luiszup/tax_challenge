package br.com.zup.tax_challenge.controller;

import br.com.zup.tax_challenge.dto.LoginDTO;
import br.com.zup.tax_challenge.dto.RegisterUserDTO;
import br.com.zup.tax_challenge.dto.UserResponseDTO;
import br.com.zup.tax_challenge.model.User;
import br.com.zup.tax_challenge.service.AuthService;
import br.com.zup.tax_challenge.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {
    @Mock
    private UserService userService;

    @Mock
    private AuthService authService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUserSuccess() {
        RegisterUserDTO request = new RegisterUserDTO();
        request.setUsername("usuarioteste");
        request.setPassword("senha123");
        request.setRoles(Collections.emptySet());

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("usuarioteste");

        when(userService.registerUser(any(RegisterUserDTO.class))).thenReturn(savedUser);

        ResponseEntity<UserResponseDTO> response = userController.registerUser(request);

        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("usuarioteste", response.getBody().getUsername());

        verify(userService, times(1)).registerUser(any(RegisterUserDTO.class));
    }

    @Test
    void registerUserFail() {
        RegisterUserDTO request = new RegisterUserDTO();
        request.setUsername("testuser");
        request.setPassword("password123");
        request.setRoles(Collections.emptySet());

        when(userService.registerUser(any(RegisterUserDTO.class))).thenThrow(new RuntimeException("Erro ao registrar usuário"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userController.registerUser(request);
        });

        assertEquals("Erro ao registrar usuário", exception.getMessage());

        verify(userService, times(1)).registerUser(any(RegisterUserDTO.class));
    }

    @Test
    void loginSuccess() {
        LoginDTO request = new LoginDTO();
        request.setUsername("usuarioteste");
        request.setPassword("senha123");

        String testToken = "token_test";

        when(authService.login(any(LoginDTO.class))).thenReturn(testToken);

        ResponseEntity<Map<String, String>> response = userController.login(request);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("token_test", response.getBody().get("token"));

        verify(authService, times(1)).login(any(LoginDTO.class));
    }

    @Test
    void loginFail() {
        LoginDTO request = new LoginDTO();
        request.setUsername("usuarioteste");
        request.setPassword("senha123");

        when(authService.login(any(LoginDTO.class))).thenThrow(new RuntimeException("Erro ao realizar login"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userController.login(request);
        });

        assertEquals("Erro ao realizar login", exception.getMessage());

        verify(authService, times(1)).login(any(LoginDTO.class));
    }
}