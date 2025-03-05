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

    @Override
    public List<TipoImposto> listAll() {
        return repository.findAll();
    }

    @Override
    public TipoImposto findTipoImposto(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imposto não encontrado"));
    }

    @Override
    public void deleteTipoImposto(Long id) {
        repository.deleteById(id);
    }
}
