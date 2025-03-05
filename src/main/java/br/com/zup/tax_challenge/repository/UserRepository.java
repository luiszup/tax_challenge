package br.com.zup.tax_challenge.repository;

import br.com.zup.tax_challenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
