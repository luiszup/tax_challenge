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

    public Imposto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getAliquota() {
        return aliquota;
    }

    public void setAliquota(Double aliquota) {
        this.aliquota = aliquota;
    }
}
