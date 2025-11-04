package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dto.LivroDTO;
import org.example.services.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> listarTodos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return ResponseEntity.ok(livroService.listarTodos(pagina, tamanho));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> buscarPorId(@PathVariable Long id) {
        LivroDTO livro = livroService.buscarPorId(id);
        return ResponseEntity.ok(livro);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<LivroDTO>> buscarPorTitulo(
            @RequestParam String titulo,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        List<LivroDTO> livros = livroService.buscarPorTitulo(titulo, pagina, tamanho);
        if (livros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<LivroDTO>> listarDisponiveis() {
        List<LivroDTO> livros = livroService.listarDisponiveis();
        if (livros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/emprestados")
    public ResponseEntity<List<LivroDTO>> listarIndisponiveis() {
        List<LivroDTO> livros = livroService.listarIndisponiveis();
        if (livros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(livros);
    }

    @PostMapping
    public ResponseEntity<LivroDTO> criar(@Valid @RequestBody LivroDTO dto) {
        LivroDTO criado = livroService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizar(@PathVariable Long id, @Valid @RequestBody LivroDTO dto) {
        LivroDTO atualizado = livroService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
