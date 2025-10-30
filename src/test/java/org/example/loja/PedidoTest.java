package org.example.loja;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    private Produto produto;
    private Cliente cliente;
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        produto = new Produto(1L, "Notebook", "Eletrônicos", new BigDecimal("2000.00"), new BigDecimal("2500.00"), 5);
        cliente = new Cliente(1L, "Maria", "12345678901", "maria@example.com", "11999999999");
        pedido = new Pedido(1L, cliente);
    }

    @Test
    void deveAdicionarItensAoPedido() {
        pedido.adicionarItem(produto, 2);
        assertEquals(1, pedido.getItens().size());
        ItemPedido item = pedido.getItens().get(0);
        assertEquals(new BigDecimal("5000.00"), item.getValorTotal());
    }

    @Test
    void deveCalcularTotalDoPedido() {
        pedido.adicionarItem(produto, 2);
        Produto mouse = new Produto(2L, "Mouse", "Acessórios", new BigDecimal("50.00"), new BigDecimal("100.00"), 10);
        pedido.adicionarItem(mouse, 1);
        assertEquals(new BigDecimal("5100.00"), pedido.calcularTotal());
    }

    @Test
    void deveLancarExcecaoFinalizarComEstoqueInsuficiente() {
        pedido.adicionarItem(produto, 6);
        assertThrows(IllegalStateException.class, pedido::finalizar);
        assertEquals(StatusPedido.ABERTO, pedido.getStatus());
    }

    @Test
    void deveFinalizarPedidoEAtualizarEstoque() {
        pedido.adicionarItem(produto, 2);
        pedido.finalizar();
        assertEquals(StatusPedido.FINALIZADO, pedido.getStatus());
        assertEquals(3, produto.getEstoque());
    }

    @Test
    void deveCancelarPedidoEReporEstoque() {
        pedido.adicionarItem(produto, 2);
        pedido.finalizar();
        pedido.cancelar();
        assertEquals(StatusPedido.CANCELADO, pedido.getStatus());
        assertEquals(5, produto.getEstoque());
    }
}
