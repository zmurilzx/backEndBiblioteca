package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "LIVRO")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 255, message = "O título deve ter no máximo 255 caracteres")
    @Column(name = "TITULO", nullable = false)
    private String titulo;

    @NotBlank(message = "O autor é obrigatório")
    @Size(max = 255, message = "O autor deve ter no máximo 255 caracteres")
    @Column(name = "AUTOR", nullable = false)
    private String autor;

    @NotBlank(message = "O ISBN é obrigatório")
    @Pattern(regexp = "^(97(8|9))?\\d{9}(\\d|X)$", message = "O ISBN deve conter 10 ou 13 dígitos válidos")
    @Size(max = 20, message = "O ISBN deve ter no máximo 20 caracteres")
    @Column(name = "ISBN", unique = true, nullable = false)
    private String isbn;

    @Column(name = "DATA_PUBLICACAO")
    @PastOrPresent(message = "A data de publicação não pode ser futura")
    private LocalDate dataPublicacao;

    @NotNull(message = "O estoque é obrigatório")
    @Min(value = 0, message = "O estoque deve ser maior ou igual a zero")
    @Column(name = "ESTOQUE")
    private Integer estoque;

    // Relacionamento ManyToOne com Fornecedor
    @NotNull(message = "O fornecedor é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FORNECEDOR_ID")
    private Fornecedor fornecedor;

    public Livro() {}

    public Livro(Long id, String titulo, String autor, String isbn, LocalDate dataPublicacao, Integer estoque, Fornecedor fornecedor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.estoque = estoque;
        this.fornecedor = fornecedor;
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public LocalDate getDataPublicacao() { return dataPublicacao; }
    public void setDataPublicacao(LocalDate dataPublicacao) { this.dataPublicacao = dataPublicacao; }

    public Integer getEstoque() { return estoque; }
    public void setEstoque(Integer estoque) { this.estoque = estoque; }

    public Fornecedor getFornecedor() { return fornecedor; }
    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }
}
