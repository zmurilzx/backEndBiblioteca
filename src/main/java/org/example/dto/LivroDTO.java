// FileName: /backEndBiblioteca/src/main/java/org/example/dto/LivroDTO.java
package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LivroDTO {

    private Long id;

    @NotBlank(message = "Título é obrigatório")
    @Size(max = 200, message = "Título deve ter no máximo 200 caracteres")
    private String titulo;

    @NotBlank(message = "Autor é obrigatório")
    @Size(max = 150, message = "Autor deve ter no máximo 150 caracteres")
    private String autor;

    @NotBlank(message = "ISBN é obrigatório")
    @Size(max = 20, message = "ISBN deve ter no máximo 20 caracteres")
    private String isbn;

    @Size(max = 100, message = "Editora deve ter no máximo 100 caracteres")
    private String editora;

    private Integer anoPublicacao;

    private Integer estoque;

    // Construtores (opcional)
    public LivroDTO() {
    }

    public LivroDTO(Long id, String titulo, String autor, String isbn, String editora, Integer anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }
}
