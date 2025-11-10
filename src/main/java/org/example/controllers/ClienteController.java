package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dto.ClienteDTO;
import org.example.dto.EnderecoDTO;
import org.example.services.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        List<ClienteDTO> clientes = clienteService.listarTodos(pagina, tamanho);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteDTO> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(clienteService.buscarPorCpf(cpf));
    }

    @GetMapping("/endereco/{cep}")
    public ResponseEntity<EnderecoDTO> buscarEnderecoPorCep(@PathVariable String cep) {
        return ResponseEntity.ok(clienteService.buscarEnderecoPorCep(cep));
    }

    @GetMapping("/rg/{rg}")
    public ResponseEntity<ClienteDTO> buscarPorRg(@PathVariable String rg) {
        return ResponseEntity.ok(clienteService.buscarPorRg(rg));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ClienteDTO>> buscarPorNome(
            @PathVariable String nome,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        List<ClienteDTO> clientes = clienteService.buscarPorNome(nome, pagina, tamanho);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/inadimplentes")
    public ResponseEntity<List<ClienteDTO>> listarInadimplentes() {
        List<ClienteDTO> clientes = clienteService.listarClientesComEmprestimosAtrasados();
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> criar(@Valid @RequestBody ClienteDTO dto) {
        ClienteDTO criado = clienteService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {
        ClienteDTO atualizado = clienteService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
