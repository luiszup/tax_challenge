package br.com.zup.tax_challenge.service;

import br.com.zup.tax_challenge.model.TipoImposto;

import java.util.List;

public interface TipoImpostoService {
    TipoImposto saveTipoImposto(TipoImposto tipoImposto);

    List<TipoImposto> listAll();

    TipoImposto findTipoImposto(Long id);

    void deleteTipoImposto(Long id);
}
