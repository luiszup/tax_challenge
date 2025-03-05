package br.com.zup.tax_challenge.dto;

import br.com.zup.tax_challenge.model.Role;

import java.util.Set;

public class RegisterUserDTO {
    private String username;
    private String password;
    private Set<Roles> roles;
}
