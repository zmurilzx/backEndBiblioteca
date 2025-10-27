package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRICAO", nullable = false, length = 50)
    private String descricao;

    @Column(name = "TIPO", nullable = false, length = 50)
    private String tipo;

    @Column(name = "NUMEROPARCELAS")
    private Integer numeroParcelas;

    @Column(name = "DIAS_ENTRE_PARCELAS")
    private Integer diasEntreParcelas;

    @Column(name = "PERMITETROCO", nullable = false)
    private boolean permiteTroco;

    @Column(name = "TAXA_PERCENTUAL", precision = 5, scale = 2)
    private java.math.BigDecimal taxaPercentual;

    @Column(name = "ATIVO")
    private Boolean ativo;


    public FormaPagamento() {}

    public FormaPagamento(String descricao, String tipo, Integer numeroParcelas,
                          Integer diasEntreParcelas, boolean permiteTroco,
                          java.math.BigDecimal taxaPercentual, boolean ativo) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.numeroParcelas = numeroParcelas;
        this.diasEntreParcelas = diasEntreParcelas;
        this.permiteTroco = permiteTroco;
        this.taxaPercentual = taxaPercentual;
        this.ativo = ativo;
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public Long setId(Long id) {
        return this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public Integer getDiasEntreParcelas() {
        return diasEntreParcelas;
    }

    public void setDiasEntreParcelas(Integer diasEntreParcelas) {
        this.diasEntreParcelas = diasEntreParcelas;
    }

    public boolean isPermiteTroco() {
        return permiteTroco;
    }

    public void setPermiteTroco(boolean permiteTroco) {
        this.permiteTroco = permiteTroco;
    }

    public java.math.BigDecimal getTaxaPercentual() {
        return taxaPercentual;
    }

    public void setTaxaPercentual(java.math.BigDecimal taxaPercentual) {
        this.taxaPercentual = taxaPercentual;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
