package br.com.zup.tax_challenge.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleIllegalArgumentException() {
        IllegalArgumentException exception = new IllegalArgumentException("Argumento inválido");
        ResponseEntity<String> response = globalExceptionHandler.handleIllegalArgumentException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Argumento inválido", response.getBody());
    }

    @Test
    void handleValidationsExceptions() {
        FieldError fieldError1 = new FieldError("objectName", "field1", "Mensagem de erro 1");
        FieldError fieldError2 = new FieldError("objectName", "field2", "Mensagem de erro 2");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError1, fieldError2));

        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);
        ResponseEntity<Map<String, Object>> response = globalExceptionHandler.handleValidationExceptions(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(400, response.getBody().get("status"));
        Map<String, String> errors = (Map<String, String>) response.getBody().get("errors");
        assertEquals(2, errors.size());
        assertEquals("Mensagem de erro 1", errors.get("field1"));
        assertEquals("Mensagem de erro 2", errors.get("field2"));
    }

    @Test
    void handleResponseStatusException() {
        ResponseStatusException exception = new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado");
        ResponseEntity<Map<String, Object>> response = globalExceptionHandler.handleResponseStatusException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(404, response.getBody().get("status"));
        assertEquals("Recurso não encontrado", response.getBody().get("error"));
    }

    @Test
    void handleRuntimeException() {
        RuntimeException exception = new RuntimeException("Erro interno");
        ResponseEntity<String> response = globalExceptionHandler.handleRuntimeException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro interno: Erro interno", response.getBody());
    }

    @Test
    void handleAccessDeniedException() {
        AccessDeniedException exception = new AccessDeniedException("Acesso negado ao recurso");
        ResponseEntity<Map<String, Object>> response = globalExceptionHandler.handleAccesDeniedException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(403, response.getBody().get("status"));
        assertEquals("Acesso negado", response.getBody().get("error"));
        assertEquals("Acesso negado ao recurso", response.getBody().get("message"));
    }
}