package br.com.zup.tax_challenge.service;

import br.com.zup.tax_challenge.model.TipoImposto;
import br.com.zup.tax_challenge.repository.TipoImpostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoImpostoServiceImpl implements TipoImpostoService{

    @Autowired
    private TipoImpostoRepository impostoRepository;

    public TipoImpostoServiceImpl(TipoImpostoRepository repository) {
        this.impostoRepository = repository;
    }

    @Override
    public TipoImposto saveTipoImposto(TipoImposto tipoImposto) {
        return impostoRepository.save(tipoImposto);
    }

    @Override
    public List<TipoImposto> listAll() {
        return impostoRepository.findAll();
    }

    @Override
    public Optional<TipoImposto> findTipoImposto(Long id) {
        return impostoRepository.findById(id);
    }

    @Override
    public void deleteTipoImposto(Long id) {
        impostoRepository.deleteById(id);
    }
}
