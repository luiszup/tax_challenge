package br.com.zup.tax_challenge.controller;

import br.com.zup.tax_challenge.dto.RegisterUserDTO;
import br.com.zup.tax_challenge.model.User;
import br.com.zup.tax_challenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<User> registerUser(@RequestBody RegisterUserDTO user) {
        User newUser = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
