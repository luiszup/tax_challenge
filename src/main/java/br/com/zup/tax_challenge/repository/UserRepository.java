package br.com.zup.tax_challenge.repository;

import br.com.zup.tax_challenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUername(String username);
    boolean existsByUsername(String username);
}
