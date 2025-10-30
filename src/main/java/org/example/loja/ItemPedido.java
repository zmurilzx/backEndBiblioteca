package org.example.loja;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ItemPedido {
    private final Produto produto;
    private final int quantidade;
    private final BigDecimal valorUnitario;
    private final BigDecimal valorTotal;

    public ItemPedido(Produto produto, int quantidade, BigDecimal valorUnitario) {
        this.produto = Objects.requireNonNull(produto, "produto");
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva");
        }
        this.quantidade = quantidade;
        if (valorUnitario == null || valorUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor unitário deve ser positivo");
        }
        this.valorUnitario = valorUnitario.setScale(2, RoundingMode.HALF_UP);
        this.valorTotal = this.valorUnitario.multiply(BigDecimal.valueOf(quantidade)).setScale(2, RoundingMode.HALF_UP);
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() {
        return produto.getNome() + " - Subtotal: R$" + valorTotal;
    }
}
