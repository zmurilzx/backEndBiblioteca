package org.example.repositories;

import org.example.entities.Emprestimo;
import org.example.enums.StatusEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findByClienteId(Long clienteId);

    List<Emprestimo> findByClienteIdAndStatusIn(Long clienteId, Collection<StatusEmprestimo> status);

    List<Emprestimo> findByBibliotecarioResponsavelIgnoreCase(String bibliotecarioResponsavel);

    List<Emprestimo> findByStatus(StatusEmprestimo status);

    List<Emprestimo> findByStatusIn(Collection<StatusEmprestimo> status);

    List<Emprestimo> findByStatusInAndDataDevolucaoPrevistaBefore(Collection<StatusEmprestimo> status, LocalDate dataLimite);
}
