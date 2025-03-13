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
        user.setUsername("usuarioteste");
        user.setPassword("senha123");
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByUsername("usuarioteste");

        assertTrue(foundUser.isPresent());
        assertEquals("usuarioteste", foundUser.get().getUsername());
    }

    @Test
    void findUsernameFail() {
        Optional<User> foundUser = userRepository.findByUsername("usuario-que-nao-existe");
        assertFalse(foundUser.isPresent());
    }

    @Test
    void existByUsernameSuccess() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123");
        userRepository.save(user);

        boolean exists = userRepository.existsByUsername("testuser");

        assertTrue(exists);
    }
}