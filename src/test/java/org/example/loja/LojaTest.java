package org.example.loja;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LojaTest {

    private Loja loja;
    private Produto notebook;
    private Produto mouse;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        loja = new Loja("Tech Store");
        notebook = new Produto(1L, "Notebook", "Eletrônicos", new BigDecimal("2000.00"), new BigDecimal("2500.00"), 10);
        mouse = new Produto(2L, "Mouse", "Acessórios", new BigDecimal("50.00"), new BigDecimal("100.00"), 20);
        cliente = new Cliente(1L, "Maria", "12345678901", "maria@example.com", "11999999999");
    }

    @Test
    void deveCadastrarProdutoECliente() {
        loja.cadastrarProduto(notebook);
        loja.cadastrarCliente(cliente);
        assertTrue(loja.getListaProdutos().contains(notebook));
        assertTrue(loja.getListaClientes().contains(cliente));
    }

    @Test
    void deveGerarRelatorioDeVendas() {
        loja.cadastrarProduto(notebook);
        loja.cadastrarCliente(cliente);
        Pedido pedido = new Pedido(1L, cliente);
        pedido.adicionarItem(notebook, 2);
        pedido.finalizar();
        loja.registrarPedido(pedido);
        assertEquals(new BigDecimal("5000.00"), loja.gerarRelatorioVendas());
    }

    @Test
    void deveBuscarProdutoPorNomeEClientePorCpf() {
        loja.cadastrarProduto(notebook);
        loja.cadastrarCliente(cliente);
        assertTrue(loja.buscarProdutoPorNome("notebook").isPresent());
        assertTrue(loja.buscarClientePorCpf("12345678901").isPresent());
    }

    @Test
    void deveListarProdutosMaisVendidos() {
        loja.cadastrarProduto(notebook);
        loja.cadastrarProduto(mouse);
        loja.cadastrarCliente(cliente);
        Pedido pedido1 = new Pedido(1L, cliente);
        pedido1.adicionarItem(notebook, 2);
        pedido1.adicionarItem(mouse, 1);
        pedido1.finalizar();
        loja.registrarPedido(pedido1);

        Pedido pedido2 = new Pedido(2L, cliente);
        pedido2.adicionarItem(mouse, 3);
        pedido2.finalizar();
        loja.registrarPedido(pedido2);

        List<Produto> maisVendidos = loja.produtosMaisVendidos();
        assertEquals(List.of(mouse, notebook), maisVendidos);
    }
}
