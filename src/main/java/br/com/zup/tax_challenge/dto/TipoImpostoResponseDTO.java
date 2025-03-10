package br.com.zup.tax_challenge.dto;

import lombok.Data;

@Data
public class TipoImpostoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Double aliquota;

    public TipoImpostoResponseDTO() {
    }

    public TipoImpostoResponseDTO(Long id, String nome, String descricao, Double aliquota) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.aliquota = aliquota;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getAliquota() {
        return aliquota;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setAliquota(Double aliquota) {
        this.aliquota = aliquota;
    }
}
