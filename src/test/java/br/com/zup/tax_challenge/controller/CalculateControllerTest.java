package br.com.zup.tax_challenge.controller;

import br.com.zup.tax_challenge.dto.CalculateRequestDTO;
import br.com.zup.tax_challenge.dto.CalculateResponseDTO;
import br.com.zup.tax_challenge.service.CalculateImpostoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculateControllerTest {
    private CalculateController calculateController;

    @Mock
    private CalculateImpostoService calculateImpostoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        calculateController = new CalculateController(calculateImpostoService);
    }

    @Test
    void calculateSuccess() {
        CalculateRequestDTO request = new CalculateRequestDTO();
        request.setTipoImpostoId(1L);
        request.setValorBase(1000.0);

        CalculateResponseDTO response = new CalculateResponseDTO();
        response.setValorFinal(1100.0);

        when(calculateImpostoService.calculateImposto(request.getTipoImpostoId(), request.getValorBase()))
                .thenReturn(response);

        ResponseEntity<CalculateResponseDTO> result = calculateController.calculate(request);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody().getValorFinal()).isEqualTo(1100.0);

        verify(calculateImpostoService, times(1))
                .calculateImposto(request.getTipoImpostoId(), request.getValorBase());
    }
}