package org.example.services;

import org.example.entities.Cliente;
import org.example.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
    }

    public Cliente buscarPorRg(String rg) {
        return clienteRepository.findByRg(rg)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com RG: " + rg));
    }

    public Cliente buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com CPF: " + cpf));
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Cliente existente = buscarPorId(id);
        existente.setNome(clienteAtualizado.getNome());
        existente.setRg(clienteAtualizado.getRg());
        existente.setCpf(clienteAtualizado.getCpf());
        existente.setSexo(clienteAtualizado.getSexo());
        existente.setObservacoes(clienteAtualizado.getObservacoes());
        existente.setAtivo(clienteAtualizado.getAtivo());
        existente.setDataCadastro(clienteAtualizado.getDataCadastro());
        existente.setDataNascimento(clienteAtualizado.getDataNascimento());
        return clienteRepository.save(existente);
    }

    public void deletar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado com ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
