package org.example.loja;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Loja {
    private final String nome;
    private final List<Produto> listaProdutos = new ArrayList<>();
    private final List<Cliente> listaClientes = new ArrayList<>();
    private final List<Pedido> listaPedidos = new ArrayList<>();

    public Loja(String nome) {
        this.nome = Objects.requireNonNull(nome, "nome");
    }

    public void cadastrarProduto(Produto produto) {
        Objects.requireNonNull(produto, "produto");
        listaProdutos.add(produto);
    }

    public void cadastrarCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "cliente");
        listaClientes.add(cliente);
    }

    public void registrarPedido(Pedido pedido) {
        Objects.requireNonNull(pedido, "pedido");
        listaPedidos.add(pedido);
    }

    public Optional<Produto> buscarProdutoPorNome(String nome) {
        return listaProdutos.stream()
                .filter(produto -> produto.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public Optional<Cliente> buscarClientePorCpf(String cpf) {
        return listaClientes.stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findFirst();
    }

    public BigDecimal gerarRelatorioVendas() {
        return listaPedidos.stream()
                .filter(pedido -> pedido.getStatus() == StatusPedido.FINALIZADO)
                .map(Pedido::calcularTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public List<Produto> produtosMaisVendidos() {
        Map<Produto, Integer> quantidadeVendida = listaPedidos.stream()
                .filter(pedido -> pedido.getStatus() == StatusPedido.FINALIZADO)
                .flatMap(pedido -> pedido.getItens().stream())
                .collect(Collectors.toMap(
                        ItemPedido::getProduto,
                        ItemPedido::getQuantidade,
                        Integer::sum));

        return quantidadeVendida.entrySet().stream()
                .sorted(Map.Entry.<Produto, Integer>comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<Produto> getListaProdutos() {
        return List.copyOf(listaProdutos);
    }

    public List<Cliente> getListaClientes() {
        return List.copyOf(listaClientes);
    }

    public List<Pedido> getListaPedidos() {
        return List.copyOf(listaPedidos);
    }

    public String getNome() {
        return nome;
    }
}
