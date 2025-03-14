package br.com.zup.tax_challenge.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TipoImpostoRequestDTO {
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotNull(message = "A alíquota é obrigatória")
    @Positive(message = "A alíquota deve ser maior que 0")
    private Double aliquota;

    public TipoImpostoRequestDTO() {
    }

    public TipoImpostoRequestDTO(String nome, String descricao, Double aliquota) {
        this.nome = nome;
        this.descricao = descricao;
        this.aliquota = aliquota;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getAliquota() {
        return aliquota;
    }
}
