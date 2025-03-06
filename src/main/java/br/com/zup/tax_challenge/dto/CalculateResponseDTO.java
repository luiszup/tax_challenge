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
}
