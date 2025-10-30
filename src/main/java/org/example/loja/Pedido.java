package org.example.loja;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Pedido {
    private final long id;
    private final Cliente cliente;
    private final List<ItemPedido> itens = new ArrayList<>();
    private final LocalDateTime data;
    private StatusPedido status = StatusPedido.ABERTO;

    public Pedido(long id, Cliente cliente) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id do pedido deve ser positivo");
        }
        this.id = id;
        this.cliente = Objects.requireNonNull(cliente, "cliente");
        this.data = LocalDateTime.now();
    }

    public void adicionarItem(Produto produto, int quantidade) {
        if (status != StatusPedido.ABERTO) {
            throw new IllegalStateException("Apenas pedidos abertos podem receber itens");
        }
        ItemPedido item = new ItemPedido(produto, quantidade, produto.getPrecoVenda());
        itens.add(item);
    }

    public BigDecimal calcularTotal() {
        return itens.stream()
                .map(ItemPedido::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public void finalizar() {
        if (status != StatusPedido.ABERTO) {
            throw new IllegalStateException("Pedido não está aberto");
        }
        for (ItemPedido item : itens) {
            Produto produto = item.getProduto();
            if (produto.getEstoque() < item.getQuantidade()) {
                throw new IllegalStateException("Estoque insuficiente para o produto " + produto.getNome());
            }
        }
        for (ItemPedido item : itens) {
            item.getProduto().baixarEstoque(item.getQuantidade());
        }
        status = StatusPedido.FINALIZADO;
    }

    public void cancelar() {
        if (status == StatusPedido.CANCELADO) {
            return;
        }
        if (status == StatusPedido.FINALIZADO) {
            for (ItemPedido item : itens) {
                item.getProduto().reporEstoque(item.getQuantidade());
            }
        }
        status = StatusPedido.CANCELADO;
    }

    public String gerarResumo() {
        StringBuilder builder = new StringBuilder();
        builder.append("Pedido ").append(id)
                .append(" - Cliente: ").append(cliente.getNome())
                .append(" - Total: R$").append(calcularTotal()).append('\n');
        for (ItemPedido item : itens) {
            builder.append(item.toString()).append('\n');
        }
        return builder.toString().trim();
    }

    public long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public LocalDateTime getData() {
        return data;
    }

    public StatusPedido getStatus() {
        return status;
    }
}
