package org.example.services;

import org.example.entities.Produto;
import org.example.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
    }

    public Produto buscarPorSku(String codigoBarras) {
        return produtoRepository.findByCodigoBarras(codigoBarras)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com Código de Barras: " + codigoBarras));
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto existente = buscarPorId(id);

        existente.setNome(produtoAtualizado.getNome());
        existente.setDescricao(produtoAtualizado.getDescricao());
        existente.setCodigoBarras(produtoAtualizado.getCodigoBarras());
        existente.setReferencia(produtoAtualizado.getReferencia());
        existente.setUnidadeMedida(produtoAtualizado.getUnidadeMedida());
        existente.setMarca(produtoAtualizado.getMarca());
        existente.setCategoria(produtoAtualizado.getCategoria());
        existente.setPrecoCusto(produtoAtualizado.getPrecoCusto());
        existente.setPrecoVenda(produtoAtualizado.getPrecoVenda());
        existente.setEstoqueAtual(produtoAtualizado.getEstoqueAtual());
        existente.setEstoqueMinimo(produtoAtualizado.getEstoqueMinimo());
        existente.setEstoqueMaximo(produtoAtualizado.getEstoqueMaximo());
        existente.setLocalizacao(produtoAtualizado.getLocalizacao());
        existente.setDataValidade(produtoAtualizado.getDataValidade());
        existente.setFornecedor(produtoAtualizado.getFornecedor());
        existente.setAtivo(produtoAtualizado.getAtivo());
        existente.setDataCadastro(produtoAtualizado.getDataCadastro());
        existente.setObservacoes(produtoAtualizado.getObservacoes());

        return produtoRepository.save(existente);
    }


    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
        produtoRepository.deleteById(id);
    }
}
