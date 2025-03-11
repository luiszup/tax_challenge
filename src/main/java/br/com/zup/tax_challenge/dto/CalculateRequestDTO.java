package br.com.zup.tax_challenge.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CalculateRequestDTO {
    @NotNull
    private Long tipoImpostoId;

    @NotNull
    @Min(0)
    private Double valorBase;

    public Long getTipoImpostoId() {
        return tipoImpostoId;
    }

    public void setTipoImpostoId(Long tipoImpostoId) {
        this.tipoImpostoId = tipoImpostoId;
    }

    public Double getValorBase() {
        return valorBase;
    }

    public void setValorBase(Double valorBase) {
        this.valorBase = valorBase;
    }
}
