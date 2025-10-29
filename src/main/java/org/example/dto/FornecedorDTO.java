// FileName: /backEndBiblioteca/src/main/java/org/example/dto/FornecedorDTO.java
package org.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class FornecedorDTO {

    private Long id;

    @NotBlank(message = "Razão social é obrigatória")
    @Size(max = 150, message = "Razão social deve ter no máximo 150 caracteres")
    private String razaoSocial;

    @Size(max = 150, message = "Nome fantasia deve ter no máximo 150 caracteres")
    private String nomeFantasia;

    @NotBlank(message = "CNPJ é obrigatório")
    @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter exatamente 14 dígitos numéricos")
    private String cnpj;

    @Size(max = 20, message = "Telefone deve ter no máximo 20 caracteres")
    private String telefone;

    @Size(max = 200, message = "Endereço deve ter no máximo 200 caracteres")
    private String endereco;

    @Size(max = 50, message = "Inscrição estadual deve ter no máximo 50 caracteres")
    private String inscricaoEstadual;

    @Size(max = 50, message = "Inscrição municipal deve ter no máximo 50 caracteres")
    private String inscricaoMunicipal;

    @Size(max = 100, message = "Contato do responsável deve ter no máximo 100 caracteres")
    private String contatoResponsavel;

    @NotNull(message = "Campo ativo é obrigatório")
    private Boolean ativo;

    @Size(max = 100, message = "Observações deve ter no máximo 100 caracteres")
    private String observacoes;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCadastro;

    // Construtores (opcional)
    public FornecedorDTO() {
    }

    public FornecedorDTO(Long id, String razaoSocial, String nomeFantasia, String cnpj, String telefone, String endereco,
                         String inscricaoEstadual, String inscricaoMunicipal, String contatoResponsavel, Boolean ativo,
                         String observacoes, LocalDateTime dataCadastro) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.endereco = endereco;
        this.inscricaoEstadual = inscricaoEstadual;
        this.inscricaoMunicipal = inscricaoMunicipal;
        this.contatoResponsavel = contatoResponsavel;
        this.ativo = ativo;
        this.observacoes = observacoes;
        this.dataCadastro = dataCadastro;
    }

    // Getters e setters

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

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
