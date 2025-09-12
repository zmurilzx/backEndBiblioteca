package org.example.services;

import org.example.dto.LivroDTO;
import org.example.entities.Livro;
import org.example.exceptions.LivroNotFoundException;
import org.example.mappers.LivroMapper;
import org.example.repositories.LivroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        // Validação simples: ISBN não pode ser vazio
        if (dto.getIsbn() == null || dto.getIsbn().isBlank()) {
            throw new IllegalArgumentException("ISBN não pode ser vazio");
        }
        Livro livro = livroMapper.toEntity(dto);
        Livro salvo = livroRepository.save(livro);
        return livroMapper.toDTO(salvo);
    }

    public LivroDTO buscarPorId(Long id) {
        return livroRepository.findById(id)
                .map(livroMapper::toDTO)
                .orElseThrow(() -> new LivroNotFoundException("Livro com ID " + id + " não encontrado."));
    }

    public List<LivroDTO> listarTodos(int pagina, int tamanho) {
        Page<Livro> page = livroRepository.findAll(PageRequest.of(pagina, tamanho));
        return page.stream()
                .map(livroMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deletar(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new LivroNotFoundException("Livro com ID " + id + " não encontrado.");
        }
        livroRepository.deleteById(id);
    }

    public LivroDTO atualizar(Long id, LivroDTO dto) {
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNotFoundException("Livro com ID " + id + " não encontrado."));

        Livro livroAtualizado = livroMapper.toEntity(dto);
        livroAtualizado.setId(livroExistente.getId());

        Livro salvo = livroRepository.save(livroAtualizado);
        return livroMapper.toDTO(salvo);
    }

    public List<LivroDTO> buscarPorTitulo(String titulo, int pagina, int tamanho) {
        Page<Livro> page = livroRepository.findByTituloContainingIgnoreCase(titulo, PageRequest.of(pagina, tamanho));
        return page.stream()
                .map(livroMapper::toDTO)
                .collect(Collectors.toList());
    }
}
