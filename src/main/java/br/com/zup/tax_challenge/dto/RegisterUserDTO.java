package br.com.zup.tax_challenge.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterUserDTO {
    private String username;
    private String password;
    private Set<Roles> roles;
}
