package br.com.zup.tax_challenge.service;

import br.com.zup.tax_challenge.dto.CalculateResponseDTO;

public interface CalculateImpostoService {
    CalculateResponseDTO calculateImposto(Long tipoImpostoId, Double valorBase);
}
