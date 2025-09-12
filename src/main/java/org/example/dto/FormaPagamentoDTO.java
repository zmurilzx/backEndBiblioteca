// FileName: /backEndBiblioteca/src/main/java/org/example/dto/FormaPagamentoDTO.java
package org.example.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FormaPagamentoDTO {

    private Long id;

    @NotBlank(message = "Tipo é obrigatório")
    private String tipo;

    @NotNull(message = "Parcelas é obrigatório")
    @Min(value = 1, message = "Parcelas deve ser no mínimo 1")
    private Integer parcelas;

    @NotNull(message = "Dias entre parcelas é obrigatório")
    @Min(value = 0, message = "Dias entre parcelas não pode ser negativo")
    private Integer diasEntreParcelas;

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public Integer getDiasEntreParcelas() {
        return diasEntreParcelas;
    }

    public void setDiasEntreParcelas(Integer diasEntreParcelas) {
        this.diasEntreParcelas = diasEntreParcelas;
    }
}
