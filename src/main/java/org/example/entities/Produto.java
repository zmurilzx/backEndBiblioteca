package org.example.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 200)
    private String nome;

    @Column(name = "DESCRICAO", length = 500)
    private String descricao;

    @Column(name = "CODIGO_BARRAS", unique = true, length = 100)
    private String codigoBarras;

    @Column(name = "REFERENCIA", length = 100)
    private String referencia;

    @Column(name = "UNIDADE_MEDIDA", nullable = false, length = 10)
    private String unidadeMedida;

    @Column(name = "MARCA", length = 100)
    private String marca;

    @Column(name = "CATEGORIA", length = 100)
    private String categoria;

    @Column(name = "PRECO_CUSTO", precision = 13, scale = 2)
    private BigDecimal precoCusto;

    @Column(name = "PRECO_VENDA", precision = 13, scale = 2)
    private BigDecimal precoVenda;

    @Column(name = "ESTOQUE_ATUAL")
    private Integer estoqueAtual;

    @Column(name = "ESTOQUE_MINIMO")
    private Integer estoqueMinimo;

    @Column(name = "ESTOQUE_MAXIMO")
    private Integer estoqueMaximo;

    @Column(name = "LOCALIZACAO", length = 100)
    private String localizacao;

    @Column(name = "DATA_VALIDADE")
    private Date dataValidade;

    @ManyToOne
    @JoinColumn(name = "FORNECEDOR_ID", referencedColumnName = "ID")
    private Fornecedor fornecedor;

    @Column(name = "ATIVO", nullable = false)
    private Boolean ativo;

    @Column(name = "DATA_CADASTRO", nullable = false, updatable = false)
    private Date dataCadastro;

    @Column(name = "OBSERVACOES", length = 1000)
    private String observacoes;

    public Produto() {}

    public Produto(String nome, String descricao, String codigoBarras, String referencia,
                   String unidadeMedida, String marca, String categoria,
                   BigDecimal precoCusto, BigDecimal precoVenda,
                   Integer estoqueAtual, Integer estoqueMinimo, Integer estoqueMaximo,
                   String localizacao, Date dataValidade,
                   Fornecedor fornecedor, Boolean ativo, String observacoes) {
        this.nome = nome;
        this.descricao = descricao;
        this.codigoBarras = codigoBarras;
        this.referencia = referencia;
        this.unidadeMedida = unidadeMedida;
        this.marca = marca;
        this.categoria = categoria;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.estoqueAtual = estoqueAtual;
        this.estoqueMinimo = estoqueMinimo;
        this.estoqueMaximo = estoqueMaximo;
        this.localizacao = localizacao;
        this.dataValidade = dataValidade;
        this.fornecedor = fornecedor;
        this.ativo = ativo;
        this.observacoes = observacoes;
    }

    // Getters e Setters

    public Long getId() {
        return id; }

    public void setId(Long id) {
        this.id = id; }

    public String getNome() {
        return nome; }

    public void setNome(String nome) {
        this.nome = nome; }

    public String getDescricao() {
        return descricao; }

    public void setDescricao(String descricao) {
        this.descricao = descricao; }

    public String getCodigoBarras() {
        return codigoBarras; }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras; }

    public String getReferencia() {
        return referencia; }

    public void setReferencia(String referencia) {
        this.referencia = referencia; }

    public String getUnidadeMedida() {
        return unidadeMedida; }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida; }

    public String getMarca() {
        return marca; }

    public void setMarca(String marca) {
        this.marca = marca; }

    public String getCategoria() {
        return categoria; }

    public void setCategoria(String categoria) {
        this.categoria = categoria; }

    public BigDecimal getPrecoCusto() {
        return precoCusto; }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto; }

    public BigDecimal getPrecoVenda() {
        return precoVenda; }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda; }

    public Integer getEstoqueAtual() {
        return estoqueAtual; }

    public void setEstoqueAtual(Integer estoqueAtual) {
        this.estoqueAtual = estoqueAtual; }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo; }

    public void setEstoqueMinimo(Integer estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo; }

    public Integer getEstoqueMaximo() {
        return estoqueMaximo; }

    public void setEstoqueMaximo(Integer estoqueMaximo) {
        this.estoqueMaximo = estoqueMaximo; }

    public String getLocalizacao() {
        return localizacao; }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao; }

    public Date getDataValidade() {
        return dataValidade; }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade; }

    public Fornecedor getFornecedor() {
        return fornecedor; }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor; }

    public Boolean getAtivo() {
        return ativo; }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo; }

    public Date getDataCadastro() {
        return dataCadastro; }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro; }

    public String getObservacoes() {
        return observacoes; }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes; }
}
