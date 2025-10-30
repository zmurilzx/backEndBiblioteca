package org.example.loja;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    private Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto(1L, "Notebook", "Eletrônicos", new BigDecimal("2000.00"), new BigDecimal("2500.00"), 10);
    }

    @Test
    void deveAplicarDescontoCorretamente() {
        produto.aplicarDesconto(new BigDecimal("0.10"));
        assertEquals(new BigDecimal("2250.00"), produto.getPrecoVenda());
    }

    @Test
    void deveReporEbaixarEstoque() {
        produto.reporEstoque(5);
        assertEquals(15, produto.getEstoque());
        produto.baixarEstoque(4);
        assertEquals(11, produto.getEstoque());
    }

    @Test
    void deveLancarExcecaoQuandoPrecoVendaMenorQueCusto() {
        assertThrows(IllegalArgumentException.class, () -> produto.setPrecoVenda(new BigDecimal("1500.00")));
    }

    @Test
    void deveCalcularMargemLucroCorretamente() {
        assertEquals(new BigDecimal("25.00"), produto.calcularMargemLucroPercentual());
    }

    @Test
    void deveCalcularValoresComDecimais() {
        Produto produtoDecimal = new Produto(2L, "Camiseta", "Vestuário", new BigDecimal("37.45"), new BigDecimal("59.90"), 5);
        produtoDecimal.aplicarDesconto(new BigDecimal("0.05"));
        assertEquals(new BigDecimal("56.91"), produtoDecimal.getPrecoVenda());
    }
}
