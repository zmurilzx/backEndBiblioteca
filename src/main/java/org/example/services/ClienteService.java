package org.example.services;

import org.example.dto.ClienteDTO;
import org.example.entities.Cliente;
import org.example.exceptions.ClienteNotFoundException;
import org.example.mappers.ClienteMapper;
import org.example.repositories.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public ClienteDTO salvar(ClienteDTO dto) {
        // Validação simples: nome e email não podem ser vazios
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser vazio");
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email do cliente não pode ser vazio");
        }
        // Validação simples de email (regex básica)
        if (!dto.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new IllegalArgumentException("Email inválido");
        }
        Cliente cliente = clienteMapper.toEntity(dto);
        Cliente salvo = clienteRepository.save(cliente);
        return clienteMapper.toDTO(salvo);
    }

    public ClienteDTO buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::toDTO)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente com ID " + id + " não encontrado."));
    }

    public List<ClienteDTO> listarTodos(int pagina, int tamanho) {
        Page<Cliente> page = clienteRepository.findAll(PageRequest.of(pagina, tamanho));
        return page.stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deletar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNotFoundException("Cliente com ID " + id + " não encontrado.");
        }
        clienteRepository.deleteById(id);
    }

    public ClienteDTO atualizar(Long id, ClienteDTO dto) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente com ID " + id + " não encontrado."));

        Cliente clienteAtualizado = clienteMapper.toEntity(dto);
        clienteAtualizado.setId(clienteExistente.getId());

        Cliente salvo = clienteRepository.save(clienteAtualizado);
        return clienteMapper.toDTO(salvo);
    }

    public List<ClienteDTO> buscarPorNome(String nome, int pagina, int tamanho) {
        Page<Cliente> page = clienteRepository.findByNomeContainingIgnoreCase(nome, PageRequest.of(pagina, tamanho));
        return page.stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }
}
