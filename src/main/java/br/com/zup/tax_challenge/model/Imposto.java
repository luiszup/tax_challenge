package br.com.zup.tax_challenge.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "impostos")
public class Imposto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Double aliquota;

    public Imposto(String nome, String descricao, Double aliquota) {
        this.nome = nome;
        this.descricao = descricao;
        this.aliquota = aliquota;
    }

    public Imposto() {
    }
}
