package br.com.zup.tax_challenge.dto;

import lombok.Data;

@Data
public class CalculateResponseDTO {
    private String tipoImposto;
    private Double valorBase;
    private Double valorImposto;
    private Double aliquota;

    public CalculateResponseDTO(String nome, Double valorBase, Double aliquota, double valorImposto) {
    }

    public String getTipoImposto() {
        return tipoImposto;
    }

    public Double getValorBase() {
        return valorBase;
    }

    public Double getValorImposto() {
        return valorImposto;
    }

    public Double getAliquota() {
        return aliquota;
    }
}
