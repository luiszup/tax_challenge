package br.com.zup.tax_challenge.service;

import br.com.zup.tax_challenge.dto.TipoImpostoRequestDTO;
import br.com.zup.tax_challenge.dto.TipoImpostoResponseDTO;
import br.com.zup.tax_challenge.model.TipoImposto;

import java.util.List;
import java.util.Optional;

public interface TipoImpostoService {
    TipoImpostoResponseDTO saveTipoImposto(TipoImpostoRequestDTO tipoImpostoRequestDTO);

    List<TipoImpostoResponseDTO> listAll();

    Optional<TipoImpostoResponseDTO> findTipoImposto(Long id);

    void deleteTipoImposto(Long id);
}
