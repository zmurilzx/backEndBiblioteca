package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.example.enums.Sexo;

import java.util.Date;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Size(max = 150, message = "O e-mail deve ter no máximo 150 caracteres")
    @Column(name = "EMAIL", nullable = false, unique = true, length = 150)
    private String email;

    @Pattern(regexp = "^$|^\\d{10,11}$", message = "O telefone deve conter 10 ou 11 dígitos")
    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    @Column(name = "TELEFONE", length = 20)
    private String telefone;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O sexo é obrigatório")
    @Column(name = "SEXO", nullable = false, length = 50)
    private Sexo sexo;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "^\\d{11}$", message = "O CPF deve conter 11 dígitos numéricos")
    @Column(name = "CPF", nullable = false, unique = true, length = 14)
    private String cpf;

    @NotBlank(message = "O RG é obrigatório")
    @Pattern(regexp = "^\\d{9}$", message = "O RG deve conter 9 dígitos numéricos")
    @Column(name = "RG", nullable = false, unique = true, length = 9)
    private String rg;

    @Column(name = "DATANASCIMENTO")
    @Past(message = "A data de nascimento deve ser uma data passada")
    private Date dataNascimento;

    @Column(name = "DATACADASTRO")
    @PastOrPresent(message = "A data de cadastro não pode ser futura")
    private Date dataCadastro;

    @Size(max = 20, message = "As observações devem ter no máximo 20 caracteres")
    @Column(name = "OBSERVACOES", length = 20)
    private String observacoes;

    @NotNull(message = "O status de ativo é obrigatório")
    @Column(name = "ATIVO", length = 50)
    private Boolean ativo;

    public Cliente() {}

    public Cliente(Long id, String nome, String email, String telefone, Sexo sexo, String cpf, String rg, Date dataNascimento, Date dataCadastro, String observacoes, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
        this.id = id;
    }
}
