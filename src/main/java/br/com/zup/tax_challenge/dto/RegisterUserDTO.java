package br.com.zup.tax_challenge.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterUserDTO {
    private String username;
    private String password;
    private Set<Roles> roles;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Roles> getRoles() {
        return roles;
    }
}
