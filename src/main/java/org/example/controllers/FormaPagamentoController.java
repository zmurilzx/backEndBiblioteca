package org.example.controllers;

import org.example.dto.FormaPagamentoDTO;
import org.example.services.FormaPagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    private final FormaPagamentoService formaPagamentoService;

    public FormaPagamentoController(FormaPagamentoService formaPagamentoService) {
        this.formaPagamentoService = formaPagamentoService;
    }

    @GetMapping
    public ResponseEntity<List<FormaPagamentoDTO>> listarTodos() {
        List<FormaPagamentoDTO> formas = formaPagamentoService.listarTodos();
        return ResponseEntity.ok(formas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamentoDTO> buscarPorId(@PathVariable Long id) {
        try {
            FormaPagamentoDTO forma = formaPagamentoService.buscarPorId(id);
            return ResponseEntity.ok(forma);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tipo")
    public ResponseEntity<List<FormaPagamentoDTO>> buscarPorTipo(
            @RequestParam String tipo,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {

        List<FormaPagamentoDTO> formas = formaPagamentoService.buscarPorTipo(tipo, pagina, tamanho);
        return ResponseEntity.ok(formas);
    }

    @PostMapping
    public ResponseEntity<FormaPagamentoDTO> criar(@RequestBody FormaPagamentoDTO dto) {
        FormaPagamentoDTO criado = formaPagamentoService.salvar(dto);
        return ResponseEntity.ok(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamentoDTO> atualizar(
            @PathVariable Long id,
            @RequestBody FormaPagamentoDTO dto) {

        try {
            FormaPagamentoDTO atualizado = formaPagamentoService.atualizar(id, dto);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            formaPagamentoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
