package org.example.services;

import org.example.dto.ClienteDTO;
import org.example.dto.EnderecoDTO;
import org.example.entities.Cliente;
import org.example.enums.Sexo;
import org.example.exceptions.ClienteNotFoundException;
import org.example.exceptions.DuplicateResourceException;
import org.example.mappers.ClienteMapper;
import org.example.repositories.ClienteRepository;
import org.example.utils.CpfValidator;
import org.example.utils.EmailValidator;
import org.example.utils.TelefoneValidator;
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
    private final CepService cepService;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper, CepService cepService) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.cepService = cepService;
    }

    public ClienteDTO salvar(ClienteDTO dto) {
        validarDadosBasicos(dto);

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

        validarDadosBasicos(dto);
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
        String cpfSanitizado = CpfValidator.sanitize(cpf);
        if (!CpfValidator.isValid(cpfSanitizado)) {
            throw new IllegalArgumentException("CPF informado é inválido");
        }
        return clienteRepository.findByCpf(cpfSanitizado)
                .map(clienteMapper::toDTO)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente com CPF " + cpf + " não encontrado."));
    }

    public EnderecoDTO buscarEnderecoPorCep(String cep) {
        return cepService.buscarEnderecoPorCep(cep);
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

    private void validarDadosBasicos(ClienteDTO dto) {
        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser vazio");
        }

        String emailNormalizado = EmailValidator.normalize(dto.getEmail());
        if (!EmailValidator.isValid(emailNormalizado)) {
            throw new IllegalArgumentException("Email do cliente é inválido");
        }
        dto.setEmail(emailNormalizado);

        String cpfSanitizado = CpfValidator.sanitize(dto.getCpf());
        if (!CpfValidator.isValid(cpfSanitizado)) {
            throw new IllegalArgumentException("CPF inválido");
        }
        dto.setCpf(cpfSanitizado);

        if (dto.getTelefone() != null && !dto.getTelefone().isBlank()) {
            String telefoneSanitizado = TelefoneValidator.sanitize(dto.getTelefone());
            if (!TelefoneValidator.isValid(telefoneSanitizado)) {
                throw new IllegalArgumentException("Telefone do cliente é inválido");
            }
            dto.setTelefone(telefoneSanitizado);
        } else {
            dto.setTelefone(null);
        }
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
