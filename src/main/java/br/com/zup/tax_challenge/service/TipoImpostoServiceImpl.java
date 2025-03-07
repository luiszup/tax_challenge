package br.com.zup.tax_challenge.service;

import br.com.zup.tax_challenge.dto.TipoImpostoRequestDTO;
import br.com.zup.tax_challenge.dto.TipoImpostoResponseDTO;
import br.com.zup.tax_challenge.model.TipoImposto;
import br.com.zup.tax_challenge.repository.TipoImpostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoImpostoServiceImpl implements TipoImpostoService{

    @Autowired
    private TipoImpostoRepository impostoRepository;

    public TipoImpostoServiceImpl(TipoImpostoRepository repository) {
        this.impostoRepository = repository;
    }

    @Override
    public TipoImpostoResponseDTO saveTipoImposto(TipoImpostoRequestDTO tipoImpostoRequestDTO) {
        TipoImposto tipoImposto = new TipoImposto(
                tipoImpostoRequestDTO.getNome(),
                tipoImpostoRequestDTO.getDescricao(),
                tipoImpostoRequestDTO.getAliquota()
        );

        TipoImposto saveImposto = impostoRepository.save(tipoImposto);
        return new TipoImpostoResponseDTO(
                saveImposto.getId(),
                saveImposto.getNome(),
                saveImposto.getDescricao(),
                saveImposto.getAliquota()
        );
    }

    @Override
    public List<TipoImpostoResponseDTO> listAll() {
        return impostoRepository.findAll().stream()
                .map(imposto -> new TipoImpostoResponseDTO(
                        imposto.getId(),
                        imposto.getNome(),
                        imposto.getDescricao(),
                        imposto.getAliquota()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TipoImpostoResponseDTO> findTipoImposto(Long id) {
        return impostoRepository.findById(id)
                .map(imposto -> new TipoImpostoResponseDTO(
                        imposto.getId(),
                        imposto.getNome(),
                        imposto.getDescricao(),
                        imposto.getAliquota()
                ));
    }

    @Override
    public void deleteTipoImposto(Long id) {
        impostoRepository.deleteById(id);
    }
}
