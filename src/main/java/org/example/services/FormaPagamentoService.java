package org.example.services;

import org.example.dto.FormaPagamentoDTO;
import org.example.entities.FormaPagamento;
import org.example.exceptions.FormaPagamentoNotFoundException;
import org.example.mappers.FormaPagamentoMapper;
import org.example.repositories.FormaPagamentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormaPagamentoService {

    private final FormaPagamentoRepository formaPagamentoRepository;
    private final FormaPagamentoMapper formaPagamentoMapper;

    public FormaPagamentoService(FormaPagamentoRepository formaPagamentoRepository,
                                 FormaPagamentoMapper formaPagamentoMapper) {
        this.formaPagamentoRepository = formaPagamentoRepository;
        this.formaPagamentoMapper = formaPagamentoMapper;
    }

    public FormaPagamentoDTO salvar(FormaPagamentoDTO dto) {
        if (dto.getTipo() == null || dto.getTipo().isBlank()) {
            throw new IllegalArgumentException("Tipo de forma de pagamento não pode ser vazio.");
        }
//        if (dto.getParcelas() != null && dto.getParcelas() < 1) {
//            throw new IllegalArgumentException("Parcelas devem ser maiores ou iguais a 1.");
//        }
        if (dto.getDiasEntreParcelas() != null && dto.getDiasEntreParcelas() < 0) {
            throw new IllegalArgumentException("Dias entre parcelas não pode ser negativo.");
        }

        FormaPagamento formaPagamento = formaPagamentoMapper.toEntity(dto);
        FormaPagamento salvo = formaPagamentoRepository.save(formaPagamento);
        return formaPagamentoMapper.toDTO(salvo);
    }

    public FormaPagamentoDTO buscarPorId(Long id) {
        return formaPagamentoRepository.findById(id)
                .map(formaPagamentoMapper::toDTO)
                .orElseThrow(() ->
                        new FormaPagamentoNotFoundException("Forma de pagamento com ID " + id + " não encontrada."));
    }

    public List<FormaPagamentoDTO> listarTodos() {
        List<FormaPagamento> lista = formaPagamentoRepository.findAll();
        return lista.stream()
                .map(formaPagamentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deletar(Long id) {
        if (!formaPagamentoRepository.existsById(id)) {
            throw new FormaPagamentoNotFoundException("Forma de pagamento com ID " + id + " não encontrada.");
        }
        formaPagamentoRepository.deleteById(id);
    }

    public FormaPagamentoDTO atualizar(Long id, FormaPagamentoDTO dto) {
        FormaPagamento existente = formaPagamentoRepository.findById(id)
                .orElseThrow(() ->
                        new FormaPagamentoNotFoundException("Forma de pagamento com ID " + id + " não encontrada."));

        FormaPagamento atualizado = formaPagamentoMapper.toEntity(dto);
        atualizado.setId(existente.getId());

        FormaPagamento salvo = formaPagamentoRepository.save(atualizado);
        return formaPagamentoMapper.toDTO(salvo);
    }

    public List<FormaPagamentoDTO> buscarPorTipo(String tipo, int pagina, int tamanho) {
        Page<FormaPagamento> page = formaPagamentoRepository
                .findByTipoContainingIgnoreCase(tipo, PageRequest.of(pagina, tamanho));

        return page.stream()
                .map(formaPagamentoMapper::toDTO)
                .collect(Collectors.toList());
    }
}
