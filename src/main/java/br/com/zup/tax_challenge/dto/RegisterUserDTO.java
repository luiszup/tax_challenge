package br.com.zup.tax_challenge.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterUserDTO {
    private String usuario;
    private String senha;
    private Set<Roles> cargos;

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public Set<Roles> getCargos() {
        return cargos;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCargos(Set<Roles> cargos) {
        this.cargos = cargos;
    }
}
