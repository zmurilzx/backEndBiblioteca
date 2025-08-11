package org.example.services;

import org.example.dto.ClienteDTO;
import org.example.entities.Cliente;
import org.example.exceptions.ClienteNotFoundException;
import org.example.mappers.ClienteMapper;
import org.example.repositories.ClienteRepository;
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
        Cliente cliente = clienteMapper.toEntity(dto);
        Cliente salvo = clienteRepository.save(cliente);
        return clienteMapper.toDTO(salvo);
    }

    public ClienteDTO buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::toDTO)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente com ID " + id + " não encontrado."));
    }

    public List<ClienteDTO> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deletar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNotFoundException("Cliente com ID " + id + " não encontrado.");
        }
        clienteRepository.deleteById(id);
    }

    public ClienteDTO buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
                .map(clienteMapper::toDTO)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente com CPF " + cpf + " não encontrado."));
    }

    public ClienteDTO buscarPorRg(String rg) {
        return clienteRepository.findByRg(rg)
                .map(clienteMapper::toDTO)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente com RG " + rg + " não encontrado."));
    }

    public ClienteDTO atualizar(Long id, ClienteDTO clienteDTO) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente com ID " + id + " não encontrado."));

        Cliente clienteAtualizado = clienteMapper.toEntity(clienteDTO);

        clienteAtualizado.setId(clienteExistente.getId());

        Cliente salvo = clienteRepository.save(clienteAtualizado);

        return clienteMapper.toDTO(salvo);
    }


}
