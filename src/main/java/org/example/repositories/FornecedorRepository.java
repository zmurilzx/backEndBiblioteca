package org.example.repositories;

import org.example.entities.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    Optional<Fornecedor> findByCnpj(String cnpj);

    Page<Fornecedor> findByRazaoSocialContainingIgnoreCaseOrNomeFantasiaContainingIgnoreCase(String razaoSocial, String nomeFantasia, Pageable pageable);
}
