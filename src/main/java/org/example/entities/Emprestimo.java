package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.example.enums.StatusEmprestimo;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "EMPRESTIMO")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull(message = "O livro é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LIVRO_ID", nullable = false)
    private Livro livro;

    @NotNull(message = "O cliente é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CLIENTE_ID", nullable = false)
    private Cliente cliente;

    @NotNull(message = "A data do empréstimo é obrigatória")
    @PastOrPresent(message = "A data do empréstimo não pode ser futura")
    @Column(name = "DATA_EMPRESTIMO", nullable = false)
    private LocalDate dataEmprestimo;

    @NotNull(message = "A data de devolução prevista é obrigatória")
    @FutureOrPresent(message = "A data de devolução prevista deve ser futura ou presente")
    @Column(name = "DATA_DEVOLUCAO_PREVISTA", nullable = false)
    private LocalDate dataDevolucaoPrevista;

    @Column(name = "DATA_DEVOLUCAO_REAL")
    @PastOrPresent(message = "A data de devolução real não pode ser futura")
    private LocalDate dataDevolucaoReal;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status é obrigatório")
    @Column(name = "STATUS", nullable = false, length = 20)
    private StatusEmprestimo status;

    @DecimalMin(value = "0.00", inclusive = true, message = "A multa deve ser maior ou igual a zero")
    @Column(name = "MULTA", precision = 10, scale = 2)
    private BigDecimal multa;

    @Size(max = 120, message = "O bibliotecário responsável deve ter no máximo 120 caracteres")
    @Column(name = "BIBLIOTECARIO_RESPONSAVEL", length = 120)
    private String bibliotecarioResponsavel;

    public Emprestimo() {
    }

    public Emprestimo(Long id, Livro livro, Cliente cliente, LocalDate dataEmprestimo,
                       LocalDate dataDevolucaoPrevista, LocalDate dataDevolucaoReal,
                       StatusEmprestimo status, BigDecimal multa, String bibliotecarioResponsavel) {
        this.id = id;
        this.livro = livro;
        this.cliente = cliente;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoReal = dataDevolucaoReal;
        this.status = status;
        this.multa = multa;
        this.bibliotecarioResponsavel = bibliotecarioResponsavel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public String getBibliotecarioResponsavel() {
        return bibliotecarioResponsavel;
    }

    public void setBibliotecarioResponsavel(String bibliotecarioResponsavel) {
        this.bibliotecarioResponsavel = bibliotecarioResponsavel;
    }
}
