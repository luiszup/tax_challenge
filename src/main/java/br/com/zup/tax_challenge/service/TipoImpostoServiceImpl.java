package br.com.zup.tax_challenge.service;

import br.com.zup.tax_challenge.model.TipoImposto;
import br.com.zup.tax_challenge.repository.TipoImpostoRepository;

import java.util.List;

public class TipoImpostoServiceImpl implements TipoImpostoService{

    private final TipoImpostoRepository repository;

    public TipoImpostoServiceImpl(TipoImpostoRepository repository) {
        this.repository = repository;
    }

    @Override
    public TipoImposto saveTipoImposto(TipoImposto tipoImposto) {
        return repository.save(tipoImposto);
    }
}
