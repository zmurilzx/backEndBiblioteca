package org.example.services;

import org.example.entities.Fornecedor;
import org.example.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    @Autowired
    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor buscarPorId(Long id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com ID: " + id));
    }

    public Fornecedor buscarPorCnpj(String cnpj) {
        return fornecedorRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com CNPJ: " + cnpj));
    }

    public void validarCnpj(String cnpj) {
        // Exemplo simples: verificar se tem 14 dígitos numéricos
        if (cnpj == null || !cnpj.matches("\\d{14}")) {
            throw new IllegalArgumentException("CNPJ inválido");
        }
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor atualizar(Long id, Fornecedor fornecedorAtualizado) {
        Fornecedor existente = buscarPorId(id);

        existente.setRazaoSocial(fornecedorAtualizado.getRazaoSocial());
        existente.setNomeFantasia(fornecedorAtualizado.getNomeFantasia());
        existente.setCnpj(fornecedorAtualizado.getCnpj());
        existente.setInscricaoEstadual(fornecedorAtualizado.getInscricaoEstadual());
        existente.setInscricaoMunicipal(fornecedorAtualizado.getInscricaoMunicipal());
        existente.setContatoResponsavel(fornecedorAtualizado.getContatoResponsavel());
        existente.setDataCadastro(fornecedorAtualizado.getDataCadastro());
        existente.setObservacoes(fornecedorAtualizado.getObservacoes());
        existente.setAtivo(fornecedorAtualizado.getAtivo());

        return fornecedorRepository.save(existente);
    }

    public void deletar(Long id) {
        if (!fornecedorRepository.existsById(id)) {
            throw new RuntimeException("Fornecedor não encontrado com ID: " + id);
        }
        fornecedorRepository.deleteById(id);
    }
}
