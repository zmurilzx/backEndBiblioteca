package org.example.loja;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Produto {
    private final long id;
    private final String nome;
    private final String categoria;
    private BigDecimal precoCusto;
    private BigDecimal precoVenda;
    private int estoque;

    public Produto(long id, String nome, String categoria, BigDecimal precoCusto, BigDecimal precoVenda, int estoque) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id do produto deve ser positivo");
        }
        this.id = id;
        this.nome = Objects.requireNonNull(nome, "nome");
        this.categoria = Objects.requireNonNull(categoria, "categoria");
        setPrecoCusto(precoCusto);
        setPrecoVenda(precoVenda);
        if (estoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }
        this.estoque = estoque;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public int getEstoque() {
        return estoque;
    }

    public void aplicarDesconto(BigDecimal percentual) {
        Objects.requireNonNull(percentual, "percentual");
        if (percentual.compareTo(BigDecimal.ZERO) < 0 || percentual.compareTo(BigDecimal.ONE) > 0) {
            throw new IllegalArgumentException("Percentual de desconto deve estar entre 0 e 1");
        }
        BigDecimal fator = BigDecimal.ONE.subtract(percentual);
        BigDecimal novoPreco = precoVenda.multiply(fator);
        if (novoPreco.compareTo(precoCusto) < 0) {
            throw new IllegalArgumentException("Preço de venda não pode ficar abaixo do preço de custo");
        }
        precoVenda = novoPreco.setScale(2, RoundingMode.HALF_UP);
    }

    public void reporEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade para reposição deve ser positiva");
        }
        estoque += quantidade;
    }

    public void baixarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade para baixa deve ser positiva");
        }
        if (quantidade > estoque) {
            throw new IllegalArgumentException("Quantidade para baixa excede estoque disponível");
        }
        estoque -= quantidade;
    }

    public BigDecimal calcularMargemLucroPercentual() {
        if (precoCusto.compareTo(BigDecimal.ZERO) == 0) {
            return precoVenda.compareTo(BigDecimal.ZERO) > 0 ? BigDecimal.valueOf(100) : BigDecimal.ZERO;
        }
        BigDecimal lucro = precoVenda.subtract(precoCusto);
        return lucro.multiply(BigDecimal.valueOf(100)).divide(precoCusto, 2, RoundingMode.HALF_UP);
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = validarPreco(precoVenda);
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        if (precoCusto == null || precoCusto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço de custo deve ser positivo");
        }
        this.precoCusto = precoCusto.setScale(2, RoundingMode.HALF_UP);
        if (this.precoVenda != null && this.precoVenda.compareTo(this.precoCusto) < 0) {
            throw new IllegalArgumentException("Preço de venda não pode ser menor que o preço de custo");
        }
    }

    private BigDecimal validarPreco(BigDecimal preco) {
        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço de venda deve ser positivo");
        }
        BigDecimal precoAjustado = preco.setScale(2, RoundingMode.HALF_UP);
        if (precoCusto != null && precoAjustado.compareTo(precoCusto) < 0) {
            throw new IllegalArgumentException("Preço de venda não pode ser menor que o preço de custo");
        }
        return precoAjustado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto produto)) return false;
        return id == produto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
