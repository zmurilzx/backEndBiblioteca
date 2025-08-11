package org.example.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "LIVRO")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITULO", nullable = false)
    private String titulo;

    @Column(name = "AUTOR", nullable = false)
    private String autor;

    @Column(name = "ISBN", unique = true, nullable = false)
    private String isbn;

    @Column(name = "DATA_PUBLICACAO")
    private LocalDate dataPublicacao;

    @Column(name = "ESTOQUE")
    private Integer estoque;

    // Relacionamento ManyToOne com Fornecedor
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
