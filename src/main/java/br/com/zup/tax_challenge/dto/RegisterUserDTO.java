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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
