package br.com.zup.tax_challenge.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tipo_impostos")
public class TipoImposto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Double aliquota;

    public TipoImposto(String nome, String descricao, Double aliquota) {
        this.nome = nome;
        this.descricao = descricao;
        this.aliquota = aliquota;
    }

    public TipoImposto() {
    }
}
