package org.example.repositories;

import org.example.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCpf(String cpf);
    Optional<Cliente> findByRg(String rg);
    Page<Cliente> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
