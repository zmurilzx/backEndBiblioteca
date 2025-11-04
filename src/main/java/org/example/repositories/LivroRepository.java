package org.example.repositories;
import org.example.entities.Livro;
import org.example.repositories.projections.FornecedorEstoqueProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByIsbn(String isbn);
    Page<Livro> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);

    List<Livro> findByEstoqueGreaterThan(int quantidade);

    List<Livro> findByEstoqueLessThanEqual(int quantidade);

    @Query("select f.id as fornecedorId, f.razaoSocial as razaoSocial, count(l) as totalTitulos, coalesce(sum(l.estoque),0) as totalEstoque " +
            "from Livro l join l.fornecedor f group by f.id, f.razaoSocial")
    List<FornecedorEstoqueProjection> buscarIndicadoresPorFornecedor();
}
