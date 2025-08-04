package org.example.services;

import org.example.entities.FormaPagamento;
import org.example.repositories.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaPagamentoService {

    private final FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    public FormaPagamentoService(FormaPagamentoRepository formaPagamentoRepository) {
        this.formaPagamentoRepository = formaPagamentoRepository;
    }

    public List<FormaPagamento> listarTodos() {
        return formaPagamentoRepository.findAll();
    }

    public FormaPagamento buscarPorId(Long id) {
        return formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Forma de pagamento não encontrada com ID: " + id));
    }

    public FormaPagamento buscarPorDescricao(String descricao) {
        return formaPagamentoRepository.findByDescricao(descricao)
                .orElseThrow(() -> new RuntimeException("Forma de pagamento não encontrada com descrição: " + descricao));
    }

    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    public FormaPagamento atualizar(Long id, FormaPagamento formaPagamentoAtualizado) {
        FormaPagamento existente = buscarPorId(id);

        existente.setDescricao(formaPagamentoAtualizado.getDescricao());
        existente.setTipo(formaPagamentoAtualizado.getTipo());
        existente.setNumeroParcelas(formaPagamentoAtualizado.getNumeroParcelas());
        existente.setDiasEntreParcelas(formaPagamentoAtualizado.getDiasEntreParcelas());
        existente.setPermiteTroco(formaPagamentoAtualizado.isPermiteTroco());
        existente.setTaxaPercentual(formaPagamentoAtualizado.getTaxaPercentual());
        existente.setAtivo(formaPagamentoAtualizado.isAtivo());

        return formaPagamentoRepository.save(existente);
    }

    public void deletar(Long id) {
        if (!formaPagamentoRepository.existsById(id)) {
            throw new RuntimeException("Forma de pagamento não encontrada com ID: " + id);
        }
        formaPagamentoRepository.deleteById(id);
    }
}
