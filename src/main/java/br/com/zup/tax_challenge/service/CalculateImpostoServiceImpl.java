package br.com.zup.tax_challenge.service;

import br.com.zup.tax_challenge.dto.CalculateResponseDTO;
import br.com.zup.tax_challenge.dto.TipoImpostoResponseDTO;
import br.com.zup.tax_challenge.model.TipoImposto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CalculateImpostoServiceImpl implements CalculateImpostoService{

    @Autowired
    private TipoImpostoService tipoImpostoService;

    public CalculateImpostoServiceImpl(TipoImpostoService tipoImpostoService) {
        this.tipoImpostoService = tipoImpostoService;
    }

    @Override
    public CalculateResponseDTO calculateImposto(Long tipoImpostoId, Double valorBase) {
        TipoImpostoResponseDTO imposto = tipoImpostoService.findTipoImposto(tipoImpostoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Imposto não encontrado"));

        double valorImposto = valorBase * (imposto.getAliquota() / 100);

        return new CalculateResponseDTO(
                imposto.getNome(),
                valorBase,
                imposto.getAliquota(),
                valorImposto
        );
    }
}
