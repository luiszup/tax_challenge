package br.com.zup.tax_challenge.repository;

import br.com.zup.tax_challenge.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
