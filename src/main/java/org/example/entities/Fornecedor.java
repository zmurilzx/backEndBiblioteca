package org.example.entities;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "RAZAOSOCIAL", nullable = false, length = 150)
    private String razaoSocial;

    @Column(name = "NOMEFANTASIA", nullable = false, length = 150)
    private String nomeFantasia;

    @Column(name = "CNPJ", nullable = false, unique = true, length = 20)
    private String cnpj;

    @Column(name = "INSCRICAOESTADUAL", length = 50)
    private String inscricaoEstadual;

    @Column(name = "INSCRICAOMUNICIPAL", length = 50)
    private String inscricaoMunicipal;

    @Column(name = "CONTATORESPONSAVEL", length = 100)
    private String contatoResponsavel;

    @Column(name = "DATACADASTRO")
    private Date dataCadastro;

    @Column(name = "ATIVO")
    private Boolean ativo;

    @Column(name = "OBSERVACOES", length = 100)
    private String observacoes;

    public Fornecedor() {
    }

    public Fornecedor(Long id, String razaoSocial, String nomeFantasia, String cnpj, String inscricaoEstadual, String inscricaoMunicipal, String contatoResponsavel, Date dataCadastro, Boolean ativo, String observacoes) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.contatoResponsavel = contatoResponsavel;
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

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
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
}
