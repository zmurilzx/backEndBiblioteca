package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dto.ClienteDTO;
import org.example.services.ClienteService;
import org.example.exceptions.ClienteNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodos() {
        List<ClienteDTO> clientes = clienteService.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/rg/{rg}")
    public ResponseEntity<ClienteDTO> buscarPorRg(@PathVariable String rg) {
        ClienteDTO cliente = clienteService.buscarPorRg(rg);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteDTO> buscarPorCpf(@PathVariable String cpf) {
        ClienteDTO cliente = clienteService.buscarPorCpf(cpf);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> criar(@Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO criado = clienteService.salvar(clienteDTO);
        return ResponseEntity.ok(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO atualizado = clienteService.atualizar(id, clienteDTO);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sexos")
    public ResponseEntity<List<Sexo>> listarSexos() {
        return ResponseEntity.ok(Arrays.asList(Sexo.values()));
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<Void> tratarNaoEncontrado() {
        return ResponseEntity.notFound().build();
    }
}
