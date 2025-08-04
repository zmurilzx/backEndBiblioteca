package org.example.controllers;

import org.example.entities.Fornecedor;
import org.example.services.FornecedorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> listarTodos() {
        List<Fornecedor> fornecedores = fornecedorService.listarTodos();
        return ResponseEntity.ok(fornecedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarPorId(@PathVariable Long id) {
        try {
            Fornecedor fornecedor = fornecedorService.buscarPorId(id);
            return ResponseEntity.ok(fornecedor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<Fornecedor> buscarPorCnpj(@PathVariable String cnpj) {
        try {
            Fornecedor fornecedor = fornecedorService.buscarPorCnpj(cnpj);
            return ResponseEntity.ok(fornecedor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Fornecedor> criar(@RequestBody Fornecedor fornecedor) {
        Fornecedor criado = fornecedorService.salvar(fornecedor);
        return ResponseEntity.ok(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> atualizar(@PathVariable Long id, @RequestBody Fornecedor fornecedorAtualizado) {
        try {
            Fornecedor atualizado = fornecedorService.atualizar(id, fornecedorAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            fornecedorService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
