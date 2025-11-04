package org.example.dto;

import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;

public class RegistrarDevolucaoRequest {

    @FutureOrPresent(message = "A devolução não pode ser registrada no passado")
    private LocalDate dataDevolucaoReal;

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }
}
