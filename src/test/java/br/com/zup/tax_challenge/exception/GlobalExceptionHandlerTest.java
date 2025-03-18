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
}