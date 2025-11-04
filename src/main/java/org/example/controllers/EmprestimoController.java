package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dto.EmprestimoDTO;
import org.example.dto.RegistrarDevolucaoRequest;
import org.example.enums.StatusEmprestimo;
import org.example.services.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    public ResponseEntity<EmprestimoDTO> registrar(@Valid @RequestBody EmprestimoDTO dto) {
        EmprestimoDTO criado = emprestimoService.registrarEmprestimo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PatchMapping("/{id}/devolucao")
    public ResponseEntity<EmprestimoDTO> registrarDevolucao(@PathVariable Long id,
                                                            @Valid @RequestBody RegistrarDevolucaoRequest request) {
        EmprestimoDTO atualizado = emprestimoService.registrarDevolucao(id, request);
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping
    public ResponseEntity<List<EmprestimoDTO>> listar(@RequestParam(required = false) StatusEmprestimo status) {
        return ResponseEntity.ok(emprestimoService.listarPorStatus(status));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<EmprestimoDTO>> listarPorCliente(@PathVariable Long clienteId,
                                                                @RequestParam(defaultValue = "false") boolean apenasAtivos) {
        return ResponseEntity.ok(emprestimoService.listarPorCliente(clienteId, apenasAtivos));
    }

    @GetMapping("/bibliotecario")
    public ResponseEntity<List<EmprestimoDTO>> listarPorBibliotecario(@RequestParam String responsavel) {
        return ResponseEntity.ok(emprestimoService.listarPorBibliotecario(responsavel));
    }

    @GetMapping("/atrasados")
    public ResponseEntity<List<EmprestimoDTO>> listarAtrasados() {
        return ResponseEntity.ok(emprestimoService.listarAtrasados());
    }
}
