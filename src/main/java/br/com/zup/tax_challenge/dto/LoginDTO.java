package br.com.zup.tax_challenge.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
