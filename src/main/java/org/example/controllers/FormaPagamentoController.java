package org.example.controllers;

import org.example.entities.FormaPagamento;
import org.example.services.FormaPagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    private final FormaPagamentoService formaPagamentoService;

    public FormaPagamentoController(FormaPagamentoService formaPagamentoService) {
        this.formaPagamentoService = formaPagamentoService;
    }

    @GetMapping
    public ResponseEntity<List<FormaPagamento>> listarTodos() {
        List<FormaPagamento> formas = formaPagamentoService.listarTodos();
        return ResponseEntity.ok(formas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamento> buscarPorId(@PathVariable Long id) {
        try {
            FormaPagamento forma = formaPagamentoService.buscarPorId(id);
            return ResponseEntity.ok(forma);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<FormaPagamento> buscarPorDescricao(@PathVariable String descricao) {
        try {
            FormaPagamento forma = formaPagamentoService.buscarPorDescricao(descricao);
            return ResponseEntity.ok(forma);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FormaPagamento> criar(@RequestBody FormaPagamento formaPagamento) {
        FormaPagamento criado = formaPagamentoService.salvar(formaPagamento);
        return ResponseEntity.ok(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamento> atualizar(@PathVariable Long id, @RequestBody FormaPagamento formaPagamentoAtualizado) {
        try {
            FormaPagamento atualizado = formaPagamentoService.atualizar(id, formaPagamentoAtualizado);
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
