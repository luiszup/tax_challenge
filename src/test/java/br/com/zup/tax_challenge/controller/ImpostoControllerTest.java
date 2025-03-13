package br.com.zup.tax_challenge.controller;

import br.com.zup.tax_challenge.dto.TipoImpostoRequestDTO;
import br.com.zup.tax_challenge.dto.TipoImpostoResponseDTO;
import br.com.zup.tax_challenge.service.TipoImpostoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ImpostoControllerTest {
    @Mock
    private TipoImpostoService impostoService;

    @InjectMocks
    private ImpostoController impostoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveImpostoSuccess() {
        TipoImpostoRequestDTO request = new TipoImpostoRequestDTO("ICMS", "Imposto sobre circulação de mercadorias", 18.0);
        TipoImpostoResponseDTO savedImposto = new TipoImpostoResponseDTO(1L, "ICMS", "Imposto sobre circulação de mercadorias", 18.0);

        when(impostoService.saveTipoImposto(any(TipoImpostoRequestDTO.class))).thenReturn(savedImposto);

        ResponseEntity<TipoImpostoResponseDTO> response = impostoController.saveTipoImposto(request);

        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("ICMS", response.getBody().getNome());

        verify(impostoService, times(1)).saveTipoImposto(any(TipoImpostoRequestDTO.class));
    }

    @Test
    void saveImpostoFail() {
        TipoImpostoRequestDTO request = new TipoImpostoRequestDTO("ICMS", "Imposto sobre circulação de mercadorias", 18.0);

        when(impostoService.saveTipoImposto(any(TipoImpostoRequestDTO.class)))
                .thenThrow(new RuntimeException("Erro ao salvar o imposto"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            impostoController.saveTipoImposto(request);
        });

        assertEquals("Erro ao salvar o imposto", exception.getMessage());

        verify(impostoService, times(1)).saveTipoImposto(any(TipoImpostoRequestDTO.class));
    }

    @Test
    void findImpostoSuccess() {
        Long id = 1L;
        TipoImpostoResponseDTO imposto = new TipoImpostoResponseDTO(1L, "ICMS", "Imposto sobre circulação de mercadorias", 18.0);

        when(impostoService.findTipoImposto(id)).thenReturn(Optional.of(imposto));

        ResponseEntity<TipoImpostoResponseDTO> response = impostoController.findTipoImposto(id);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("ICMS", response.getBody().getNome());

        verify(impostoService, times(1)).findTipoImposto(id);
    }

    @Test
    void findImpostoFail() {
        Long id = 1L;
        when(impostoService.findTipoImposto(id)).thenReturn(Optional.empty());

        ResponseEntity<TipoImpostoResponseDTO> response = impostoController.findTipoImposto(id);

        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());

        verify(impostoService, times(1)).findTipoImposto(id);
    }

    @Test
    void listAllSuccess() {
        TipoImpostoResponseDTO imposto1 = new TipoImpostoResponseDTO(1L, "ICMS", "Imposto sobre circulação de mercadorias", 18.0);
        TipoImpostoResponseDTO imposto2 = new TipoImpostoResponseDTO(2L, "ISS", "Imposto sobre serviços", 5.0);

        when(impostoService.listAll()).thenReturn(Arrays.asList(imposto1, imposto2));

        ResponseEntity<List<TipoImpostoResponseDTO>> response = impostoController.listAll();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());

        verify(impostoService, times(1)).listAll();
    }

    @Test
    void deleteSuccess() {
        Long id = 1L;
        doNothing().when(impostoService).deleteTipoImposto(id);

        ResponseEntity<Void> response = impostoController.deleteTipoImposto(id);

        assertNotNull(response);
        assertEquals(204, response.getStatusCodeValue());

        verify(impostoService, times(1)).deleteTipoImposto(id);
    }
}