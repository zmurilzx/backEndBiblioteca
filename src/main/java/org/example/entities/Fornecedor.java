package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "A razão social é obrigatória")
    @Size(max = 150, message = "A razão social deve ter no máximo 150 caracteres")
    @Column(name = "RAZAOSOCIAL", nullable = false, length = 150)
    private String razaoSocial;

    @NotBlank(message = "O nome fantasia é obrigatório")
    @Size(max = 150, message = "O nome fantasia deve ter no máximo 150 caracteres")
    @Column(name = "NOMEFANTASIA", nullable = false, length = 150)
    private String nomeFantasia;

    @NotBlank(message = "O CNPJ é obrigatório")
    @Pattern(regexp = "^\\d{14}$", message = "O CNPJ deve conter 14 dígitos numéricos")
    @Size(max = 20, message = "O CNPJ deve ter no máximo 20 caracteres")
    @Column(name = "CNPJ", nullable = false, unique = true, length = 20)
    private String cnpj;

    @Pattern(regexp = "^$|^\\w{3,50}$", message = "A inscrição estadual deve ter entre 3 e 50 caracteres")
    @Column(name = "INSCRICAOESTADUAL", length = 50)
    private String inscricaoEstadual;

    @Pattern(regexp = "^$|^\\w{3,50}$", message = "A inscrição municipal deve ter entre 3 e 50 caracteres")
    @Column(name = "INSCRICAOMUNICIPAL", length = 50)
    private String inscricaoMunicipal;

    @Size(max = 100, message = "O contato responsável deve ter no máximo 100 caracteres")
    @Column(name = "CONTATORESPONSAVEL", length = 100)
    private String contatoResponsavel;

    @Pattern(regexp = "^$|^\\d{10,11}$", message = "O telefone deve conter 10 ou 11 dígitos")
    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    @Column(name = "TELEFONE", length = 20)
    private String telefone;

    @Size(max = 200, message = "O endereço deve ter no máximo 200 caracteres")
    @Column(name = "ENDERECO", length = 200)
    private String endereco;

    @Column(name = "DATACADASTRO")
    @PastOrPresent(message = "A data de cadastro não pode ser futura")
    private LocalDateTime dataCadastro;

    @NotNull(message = "O status de ativo é obrigatório")
    @Column(name = "ATIVO")
    private Boolean ativo;

    @Size(max = 100, message = "As observações devem ter no máximo 100 caracteres")
    @Column(name = "OBSERVACOES", length = 100)
    private String observacoes;

    public Fornecedor() {
    }

    public Fornecedor(Long id, String razaoSocial, String nomeFantasia, String cnpj, String inscricaoEstadual, String inscricaoMunicipal, String contatoResponsavel, String telefone, String endereco, LocalDateTime dataCadastro, Boolean ativo, String observacoes) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.contatoResponsavel = contatoResponsavel;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
        this.observacoes = observacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String getContatoResponsavel() {
        return contatoResponsavel;
    }

    public void setContatoResponsavel(String contatoResponsavel) {
        this.contatoResponsavel = contatoResponsavel;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @PrePersist
    public void preencherDataCadastro() {
        if (dataCadastro == null) {
            dataCadastro = LocalDateTime.now();
        }
    }
}
