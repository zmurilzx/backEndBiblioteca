package org.example.services;

import org.example.dto.LivroDTO;
import org.example.entities.Livro;
import org.example.exceptions.ClienteNotFoundException;
import org.example.mappers.LivroMapper;
import org.example.repositories.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;

    public LivroService(LivroRepository livroRepository, LivroMapper livroMapper) {
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
    }

    public LivroDTO salvar(LivroDTO dto) {
        Livro livro = livroMapper.toEntity(dto);
        Livro salvo = livroRepository.save(livro);
        return livroMapper.toDTO(salvo);
    }

    public LivroDTO buscarPorId(Long id) {
        return livroRepository.findById(id)
                .map(livroMapper::toDTO)
                .orElseThrow(() -> new ClienteNotFoundException("Livro com ID " + id + " não encontrado."));
    }

    public List<LivroDTO> listarTodos() {
        return livroRepository.findAll()
                .stream()
                .map(livroMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deletar(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new ClienteNotFoundException("Livro com ID " + id + " não encontrado.");
        }
        livroRepository.deleteById(id);
    }

    public LivroDTO atualizar(Long id, LivroDTO dto) {
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Livro com ID " + id + " não encontrado."));

        Livro livroAtualizado = livroMapper.toEntity(dto);
        livroAtualizado.setId(livroExistente.getId());

        Livro salvo = livroRepository.save(livroAtualizado);
        return livroMapper.toDTO(salvo);
    }
}
