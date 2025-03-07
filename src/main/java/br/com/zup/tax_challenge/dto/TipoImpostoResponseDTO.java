package br.com.zup.tax_challenge.dto;

import lombok.Data;

@Data
public class TipoImpostoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Double aliquota;

    public TipoImpostoResponseDTO(Long id, String nome, String descricao, Double aliquota) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.aliquota = aliquota;
    }
}
