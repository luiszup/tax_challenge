package br.com.zup.tax_challenge.controller;

import br.com.zup.tax_challenge.dto.RegisterUserDTO;
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

        ResponseEntity<User> response = userController.registerUser(request);

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
}