package org.example.services;

import org.example.dto.ClienteDTO;
import org.example.entities.Cliente;
import org.example.enums.Sexo;
import org.example.exceptions.ClienteNotFoundException;
import org.example.exceptions.DuplicateResourceException;
import org.example.mappers.ClienteMapper;
import org.example.repositories.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (dto.getNome() == null || dto.getNome().isBlank())
            throw new IllegalArgumentException("Nome do cliente não pode ser vazio");
        if (dto.getEmail() == null || dto.getEmail().isBlank())
            throw new IllegalArgumentException("Email do cliente não pode ser vazio");

        validarSexo(dto.getSexo());
        validarDuplicidades(dto, null);

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
        return page.stream().map(clienteMapper::toDTO).collect(Collectors.toList());
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

        validarSexo(dto.getSexo());
        validarDuplicidades(dto, clienteExistente.getId());

        Cliente clienteAtualizado = clienteMapper.toEntity(dto);
        clienteAtualizado.setId(clienteExistente.getId());

        Cliente salvo = clienteRepository.save(clienteAtualizado);
        return clienteMapper.toDTO(salvo);
    }

    public List<ClienteDTO> buscarPorNome(String nome, int pagina, int tamanho) {
        Page<Cliente> page = clienteRepository.findByNomeContainingIgnoreCase(nome, PageRequest.of(pagina, tamanho));
        return page.stream().map(clienteMapper::toDTO).collect(Collectors.toList());
    }

    public ClienteDTO buscarPorRg(String rg) {
        return clienteRepository.findByRg(rg)
                .map(clienteMapper::toDTO)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente com RG " + rg + " não encontrado."));
    }

    public ClienteDTO buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
                .map(clienteMapper::toDTO)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente com CPF " + cpf + " não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> listarClientesComEmprestimosAtrasados() {
        return clienteRepository.findClientesComEmprestimosAtrasados().stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    private void validarDuplicidades(ClienteDTO dto, Long idExistente) {
        clienteRepository.findByCpf(dto.getCpf())
                .filter(cliente -> !cliente.getId().equals(idExistente))
                .ifPresent(cliente -> {
                    throw new DuplicateResourceException("Já existe um cliente cadastrado com o CPF informado.");
                });

        clienteRepository.findByRg(dto.getRg())
                .filter(cliente -> !cliente.getId().equals(idExistente))
                .ifPresent(cliente -> {
                    throw new DuplicateResourceException("Já existe um cliente cadastrado com o RG informado.");
                });

        clienteRepository.findByEmail(dto.getEmail())
                .filter(cliente -> !cliente.getId().equals(idExistente))
                .ifPresent(cliente -> {
                    throw new DuplicateResourceException("Já existe um cliente cadastrado com o email informado.");
                });
    }

    private void validarSexo(String sexo) {
        if (sexo == null) {
            throw new IllegalArgumentException("Sexo é obrigatório");
        }
        try {
            Sexo.valueOf(sexo.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Sexo inválido. Valores aceitos: MASCULINO, FEMININO ou OUTRO.");
        }
    }
}
