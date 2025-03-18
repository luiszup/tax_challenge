package br.com.zup.tax_challenge.repository;

import br.com.zup.tax_challenge.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findUsernameSuccess() {
        User user = new User();
        user.setUsuario("usuarioteste");
        user.setSenha("senha123");
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByUsuario("usuarioteste");

        assertTrue(foundUser.isPresent());
        assertEquals("usuarioteste", foundUser.get().getUsuario());
    }

    @Test
    void findUsernameFail() {
        Optional<User> foundUser = userRepository.findByUsuario("usuario-que-nao-existe");
        assertFalse(foundUser.isPresent());
    }

    @Test
    void existByUsernameSuccess() {
        User user = new User();
        user.setUsuario("testuser");
        user.setSenha("password123");
        userRepository.save(user);

        boolean exists = userRepository.existsByUsuario("testuser");

        assertTrue(exists);
    }

    @Test
    void existByUsernameFail() {
        boolean exists = userRepository.existsByUsuario("usuario-que-nao-existe");

        assertFalse(exists);
    }
}