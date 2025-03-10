package br.com.zup.tax_challenge.dto;

import lombok.Data;

@Data
public class CalculateResponseDTO {
    private String tipoImposto;
    private Double valorBase;
    private Double valorImposto;
    private Double aliquota;

    public CalculateResponseDTO() {
    }

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

    public void setValorBase(Double valorBase) {
        this.valorBase = valorBase;
    }

    public void setValorImposto(Double valorImposto) {
        this.valorImposto = valorImposto;
    }

    public void setAliquota(Double aliquota) {
        this.aliquota = aliquota;
    }

    public void setTipoImposto(String tipoImposto) {
        this.tipoImposto = tipoImposto;
    }
}
