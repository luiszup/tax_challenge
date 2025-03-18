package br.com.zup.tax_challenge.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String usuario;
    private String senha;

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
