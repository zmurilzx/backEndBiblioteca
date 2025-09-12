package org.example.repositories;

import org.example.entities.FormaPagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
    Page<FormaPagamento> findByTipoContainingIgnoreCase(String tipo, Pageable pageable);
}
