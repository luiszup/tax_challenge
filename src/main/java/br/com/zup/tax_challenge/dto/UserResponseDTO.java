package br.com.zup.tax_challenge.dto;

import java.util.Set;

public class UserResponseDTO {
    private Long id;
    private String usuario;
    private Set<String> cargos;

    public UserResponseDTO(Long id, String usuario, Set<String> cargos) {
        this.id = id;
        this.usuario = usuario;
        this.cargos = cargos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Set<String> getCargos() {
        return cargos;
    }

    public void setCargos(Set<String> cargos) {
        this.cargos = cargos;
    }
}
