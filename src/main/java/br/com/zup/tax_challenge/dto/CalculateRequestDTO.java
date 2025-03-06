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
}
