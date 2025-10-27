// FileName: /backEndBiblioteca/src/main/java/org/example/dto/FormaPagamentoDTO.java
package org.example.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class FormaPagamentoDTO {

    private Long id;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "Tipo é obrigatório")
    private String tipo;

    // pode ser nulo (ex.: pagamento à vista), mas se informado deve ser >= 1
    @Min(value = 1, message = "Número de parcelas deve ser no mínimo 1")
    private Integer numeroParcelas;

    @NotNull(message = "Dias entre parcelas é obrigatório")
    @Min(value = 0, message = "Dias entre parcelas não pode ser negativo")
    private Integer diasEntreParcelas;

    // usar Boolean no DTO para permitir ausência do campo (ex.: ao criar sem informar)
    @NotNull(message = "PermiteTroco é obrigatório")
    private Boolean permiteTroco;

    @DecimalMin(value = "0.00", inclusive = true, message = "Taxa percentual não pode ser negativa")
    private BigDecimal taxaPercentual;

    @NotNull(message = "Ativo é obrigatório")
    private Boolean ativo;

    public FormaPagamentoDTO() {
    }

    // Construtor com todos os campos (opcional)
    public FormaPagamentoDTO(Long id, String descricao, String tipo, Integer numeroParcelas,
                             Integer diasEntreParcelas, Boolean permiteTroco,
                             BigDecimal taxaPercentual, Boolean ativo) {
        this.id = id;
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

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getPermiteTroco() {
        return permiteTroco;
    }

    public void setPermiteTroco(Boolean permiteTroco) {
        this.permiteTroco = permiteTroco;
    }

    public BigDecimal getTaxaPercentual() {
        return taxaPercentual;
    }

    public void setTaxaPercentual(BigDecimal taxaPercentual) {
        this.taxaPercentual = taxaPercentual;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}