package br.com.zup.tax_challenge.repository;

import br.com.zup.tax_challenge.model.TipoImposto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoImpostoRepository extends JpaRepository<TipoImposto, Long> {
}
