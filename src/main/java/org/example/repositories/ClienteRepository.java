package org.example.repositories;

import org.example.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCpf(String cpf);
    Optional<Cliente> findByRg(String rg);
    Optional<Cliente> findByEmail(String email);
    Page<Cliente> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    @Query("select distinct e.cliente from Emprestimo e where e.status = org.example.enums.StatusEmprestimo.ATRASADO")
    List<Cliente> findClientesComEmprestimosAtrasados();
}
