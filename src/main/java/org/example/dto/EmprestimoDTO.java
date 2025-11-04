package org.example.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.enums.StatusEmprestimo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmprestimoDTO {

    private Long id;

    @NotNull(message = "O livro é obrigatório")
    private Long livroId;

    private String livroTitulo;

    @NotNull(message = "O cliente é obrigatório")
    private Long clienteId;

    private String clienteNome;

    @Size(max = 120, message = "O nome do bibliotecário deve ter no máximo 120 caracteres")
    private String bibliotecarioResponsavel;

    @PastOrPresent(message = "A data de empréstimo não pode estar no futuro")
    private LocalDate dataEmprestimo;

    @NotNull(message = "A data de devolução prevista é obrigatória")
    @FutureOrPresent(message = "A devolução prevista não pode estar no passado")
    private LocalDate dataDevolucaoPrevista;

    private LocalDate dataDevolucaoReal;

    private StatusEmprestimo status;

    private BigDecimal multa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }

    public String getLivroTitulo() {
        return livroTitulo;
    }

    public void setLivroTitulo(String livroTitulo) {
        this.livroTitulo = livroTitulo;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getBibliotecarioResponsavel() {
        return bibliotecarioResponsavel;
    }

    public void setBibliotecarioResponsavel(String bibliotecarioResponsavel) {
        this.bibliotecarioResponsavel = bibliotecarioResponsavel;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }

    public StatusEmprestimo getStatus() {
        return status;
    }

    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }

    public BigDecimal getMulta() {
        return multa;
    }

    public void setMulta(BigDecimal multa) {
        this.multa = multa;
    }
}
