package br.com.zup.tax_challenge.service;

import br.com.zup.tax_challenge.dto.CalculateResponseDTO;
import br.com.zup.tax_challenge.dto.TipoImpostoResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculateImpostoServiceImplTest {

    @Mock
    private TipoImpostoService tipoImpostoService;

    @InjectMocks
    private CalculateImpostoServiceImpl calculateImpostoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateImpostoSuccess() {
        Long tipoImpostoId = 1L;
        Double valorBase = 1000.0;
        TipoImpostoResponseDTO tipoImpostoResponseDTO = new TipoImpostoResponseDTO();
        tipoImpostoResponseDTO.setNome("ICMS");
        tipoImpostoResponseDTO.setAliquota(18.0);

        when(tipoImpostoService.findTipoImposto(tipoImpostoId)).thenReturn(Optional.of(tipoImpostoResponseDTO));

        CalculateResponseDTO response = calculateImpostoService.calculateImposto(tipoImpostoId, valorBase);

        assertNotNull(response);
        assertEquals("ICMS", response.getTipoImposto());
        assertEquals(1000.0, response.getValorBase());
        assertEquals(18.0, response.getAliquota());
        assertEquals(180.0, response.getValorImposto());

        verify(tipoImpostoService, times(1)).findTipoImposto(tipoImpostoId);
    }

    @Test
    void calculateImpostoFail() {
        Long tipoImpostoId = 1L;
        Double valorBase = 1000.0;

        when(tipoImpostoService.findTipoImposto(tipoImpostoId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            calculateImpostoService.calculateImposto(tipoImpostoId, valorBase);
        });

        assertEquals("404 NOT_FOUND \"Imposto não encontrado\"", exception.getMessage());

        verify(tipoImpostoService, times(1)).findTipoImposto(tipoImpostoId);
    }
}