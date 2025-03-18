package br.com.zup.tax_challenge.service;

import br.com.zup.tax_challenge.dto.RegisterUserDTO;
import br.com.zup.tax_challenge.model.User;
import br.com.zup.tax_challenge.model.Role;
import br.com.zup.tax_challenge.repository.RoleRepository;
import br.com.zup.tax_challenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterUserDTO registerUserDTO) {
        if (userRepository.existsByUsername(registerUserDTO.getUsuario())) {
            throw new RuntimeException("O usuário já existe");
        }

        User user = new User();
        user.setUsuario(registerUserDTO.getUsuario());
        user.setSenha(passwordEncoder.encode(registerUserDTO.getSenha()));

        Set<Role> roles = registerUserDTO.getCargos().stream()
                .map(r -> new Role(r.name())).collect(Collectors.toSet());
        roleRepository.saveAll(roles);

        user.setCargos(roles);
        User savedUser = userRepository.save(user);

        return savedUser;
    }
}
