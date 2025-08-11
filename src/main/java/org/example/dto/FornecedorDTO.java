package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FornecedorDTO {

    private Long id;

    @NotBlank(message = "Nome do fornecedor é obrigatório")
    @Size(max = 150)
    private String nome;

    @Size(max = 100)
    private String contato;

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }
}
