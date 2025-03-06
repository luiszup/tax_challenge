package br.com.zup.tax_challenge.controller;

import br.com.zup.tax_challenge.dto.LoginDTO;
import br.com.zup.tax_challenge.dto.RegisterUserDTO;
import br.com.zup.tax_challenge.model.User;
import br.com.zup.tax_challenge.service.AuthService;
import br.com.zup.tax_challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUserDTO user) {
        User newUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
        String token = authService.login(loginDTO);
        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
}
