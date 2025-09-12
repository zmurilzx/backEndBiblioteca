package org.example.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Enumerated(EnumType.STRING) // Salva como texto no banco
    @Column(name = "SEXO", nullable = false, length = 50)
    private Sexo sexo;

    @Column(name = "CPF", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name = "RG", nullable = false, unique = true, length = 9)
    private String rg;

    @Column(name = "DATANASCIMENTO")
    private Date dataNascimento;

    @Column(name = "DATACADASTRO")
    private Date dataCadastro;

    @Column(name = "OBSERVACOES", length = 20)
    private String observacoes;

    @Column(name = "ATIVO", length = 50)
    private Boolean ativo;

    public Cliente() {}

    public Cliente(Long id, String nome, Sexo sexo, String cpf, String rg, Date dataNascimento, Date dataCadastro, String observacoes, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.cpf = cpf;
        this.rg = rg;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
        this.observacoes = observacoes;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void setId(Long id) {
    }
}
