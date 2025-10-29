package org.example.services;

import org.example.dto.FornecedorDTO;
import org.example.entities.Fornecedor;
import org.example.exceptions.DuplicateResourceException;
import org.example.exceptions.FornecedorNotFoundException;
import org.example.mappers.FornecedorMapper;
import org.example.repositories.FornecedorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final FornecedorMapper fornecedorMapper;

    public FornecedorService(FornecedorRepository fornecedorRepository, FornecedorMapper fornecedorMapper) {
        this.fornecedorRepository = fornecedorRepository;
        this.fornecedorMapper = fornecedorMapper;
    }

    public List<FornecedorDTO> listarTodos() {
        return fornecedorRepository.findAll().stream()
                .map(fornecedorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FornecedorDTO buscarPorId(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new FornecedorNotFoundException("Fornecedor não encontrado com ID: " + id));
        return fornecedorMapper.toDTO(fornecedor);
    }

    public FornecedorDTO buscarPorCnpj(String cnpj) {
        Fornecedor fornecedor = fornecedorRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new FornecedorNotFoundException("Fornecedor não encontrado com CNPJ: " + cnpj));
        return fornecedorMapper.toDTO(fornecedor);
    }

    public FornecedorDTO salvar(FornecedorDTO dto) {
        validarCnpj(dto.getCnpj());
        verificarDuplicidadeCnpj(dto.getCnpj(), null);

        Fornecedor fornecedor = fornecedorMapper.toEntity(dto);
        Fornecedor salvo = fornecedorRepository.save(fornecedor);
        return fornecedorMapper.toDTO(salvo);
    }

    public FornecedorDTO atualizar(Long id, FornecedorDTO dto) {
        Fornecedor existente = fornecedorRepository.findById(id)
                .orElseThrow(() -> new FornecedorNotFoundException("Fornecedor não encontrado com ID: " + id));

        validarCnpj(dto.getCnpj());
        verificarDuplicidadeCnpj(dto.getCnpj(), existente.getId());

        LocalDateTime dataCadastro = existente.getDataCadastro();
        fornecedorMapper.updateEntityFromDto(dto, existente);
        if (dto.getDataCadastro() == null) {
            existente.setDataCadastro(dataCadastro);
        }

        Fornecedor salvo = fornecedorRepository.save(existente);
        return fornecedorMapper.toDTO(salvo);
    }

    public void deletar(Long id) {
        if (!fornecedorRepository.existsById(id)) {
            throw new FornecedorNotFoundException("Fornecedor não encontrado com ID: " + id);
        }
        fornecedorRepository.deleteById(id);
    }

    private void validarCnpj(String cnpj) {
        if (cnpj == null || !cnpj.matches("\\d{14}")) {
            throw new IllegalArgumentException("CNPJ deve conter exatamente 14 dígitos numéricos.");
        }
    }

    private void verificarDuplicidadeCnpj(String cnpj, Long idExistente) {
        fornecedorRepository.findByCnpj(cnpj)
                .filter(fornecedor -> !fornecedor.getId().equals(idExistente))
                .ifPresent(fornecedor -> {
                    throw new DuplicateResourceException("Já existe um fornecedor cadastrado com o CNPJ informado.");
                });
    }
}
