package br.com.zup.tax_challenge.service;

import br.com.zup.tax_challenge.dto.TipoImpostoRequestDTO;
import br.com.zup.tax_challenge.dto.TipoImpostoResponseDTO;
import br.com.zup.tax_challenge.model.TipoImposto;
import br.com.zup.tax_challenge.repository.TipoImpostoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class TipoImpostoServiceImplTest {
    @Mock
    private TipoImpostoRepository impostoRepository;

    @InjectMocks
    private TipoImpostoServiceImpl impostoService;

    @BeforeEach
    void serUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveImpostoSuccess() {
        TipoImpostoRequestDTO request = new TipoImpostoRequestDTO("ICMS", "Imposto sobre circulação de mercadorias", 18.0);
        TipoImposto savedImposto = new TipoImposto(1L, "ICMS", "Imposto sobre circulação de mercadorias", 18.0);

        when(impostoRepository.save(any(TipoImposto.class))).thenReturn(savedImposto);

        TipoImpostoResponseDTO response = impostoService.saveTipoImposto(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("ICMS", response.getNome());
        assertEquals("Imposto sobre circulação de mercadorias", response.getDescricao());
        assertEquals(18.0, response.getAliquota());

        verify(impostoRepository, times(1)).save(any(TipoImposto.class));
    }

    @Test
    void listAllSuccess() {
        TipoImposto imposto1 = new TipoImposto(1L, "ICMS", "Imposto sobre circulação de mercadorias", 18.0);
        TipoImposto imposto2 = new TipoImposto(1L, "ISS", "Imposto sobre serviços", 5.0);

        when(impostoRepository.findAll()).thenReturn(Arrays.asList(imposto1, imposto2));

        List<TipoImpostoResponseDTO> responseList = impostoService.listAll();

        assertNotNull(responseList);
        assertEquals(2, responseList.size());
        assertEquals("ICMS", responseList.get(0).getNome());
        assertEquals("ISS", responseList.get(1).getNome());

        verify(impostoRepository, times(1)).findAll();
    }

    @Test
    void findImpostoSuccess() {
        Long id = 1L;
        TipoImposto imposto = new TipoImposto(1L, "ICMS", "Imposto sobre circulação de mercadorias", 18.0);

        when(impostoRepository.findById(id)).thenReturn(Optional.of(imposto));

        Optional<TipoImpostoResponseDTO> response = impostoService.findTipoImposto(id);

        assertTrue(response.isPresent());
        assertEquals("ICMS", response.get().getNome());
        assertEquals(18.0, response.get().getAliquota());

        verify(impostoRepository, times(1)).findById(id);
    }

    @Test
    void deleteSuccess() {
        Long id = 1L;

        doNothing().when(impostoRepository).deleteById(id);

        impostoService.deleteTipoImposto(id);

        verify(impostoRepository, times(1)).deleteById(id);
    }

    @Test
    void saveImpostoFail() {
        TipoImpostoRequestDTO request = new TipoImpostoRequestDTO("ICMS", "Imposto sobre circulação de mercadorias", 18.0);

        when(impostoRepository.save(any(TipoImposto.class))).thenThrow(new RuntimeException("Erro ao salvar no banco de dados"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            impostoService.saveTipoImposto(request);
        });

        assertEquals("Erro ao salvar no banco de dados", exception.getMessage());

        verify(impostoRepository, times(1)).save(any(TipoImposto.class));
    }

    @Test
    void listAllFail() {
        when(impostoRepository.findAll()).thenThrow(new RuntimeException("Erro ao buscar dados"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            impostoService.listAll();
        });

        assertEquals("Erro ao buscar dados", exception.getMessage());

        verify(impostoRepository, times(1)).findAll();
    }

    @Test
    void findImpostoFail() {
        Long tipoImpostoId = 1L;

        when(impostoRepository.findById(tipoImpostoId)).thenReturn(Optional.empty());

        Optional<TipoImpostoResponseDTO> result = impostoService.findTipoImposto(tipoImpostoId);

        assertTrue(result.isEmpty());

        verify(impostoRepository, times(1)).findById(tipoImpostoId);
    }
}