package br.com.zup.tax_challenge.service;

import br.com.zup.tax_challenge.model.TipoImposto;

import java.util.List;
import java.util.Optional;

public interface TipoImpostoService {
    TipoImposto saveTipoImposto(TipoImposto tipoImposto);

    List<TipoImposto> listAll();

    Optional<TipoImposto> findTipoImposto(Long id);

    void deleteTipoImposto(Long id);
}
