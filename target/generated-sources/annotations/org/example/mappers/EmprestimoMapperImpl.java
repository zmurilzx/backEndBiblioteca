package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.dto.EmprestimoDTO;
import org.example.entities.Cliente;
import org.example.entities.Emprestimo;
import org.example.entities.Livro;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-10T14:46:12-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.17 (Microsoft)"
)
@Component
public class EmprestimoMapperImpl implements EmprestimoMapper {

    @Override
    public EmprestimoDTO toDTO(Emprestimo emprestimo) {
        if ( emprestimo == null ) {
            return null;
        }

        EmprestimoDTO emprestimoDTO = new EmprestimoDTO();

        emprestimoDTO.setLivroId( emprestimoLivroId( emprestimo ) );
        emprestimoDTO.setLivroTitulo( emprestimoLivroTitulo( emprestimo ) );
        emprestimoDTO.setClienteId( emprestimoClienteId( emprestimo ) );
        emprestimoDTO.setClienteNome( emprestimoClienteNome( emprestimo ) );
        emprestimoDTO.setId( emprestimo.getId() );
        emprestimoDTO.setBibliotecarioResponsavel( emprestimo.getBibliotecarioResponsavel() );
        emprestimoDTO.setDataEmprestimo( emprestimo.getDataEmprestimo() );
        emprestimoDTO.setDataDevolucaoPrevista( emprestimo.getDataDevolucaoPrevista() );
        emprestimoDTO.setDataDevolucaoReal( emprestimo.getDataDevolucaoReal() );
        emprestimoDTO.setStatus( emprestimo.getStatus() );
        emprestimoDTO.setMulta( emprestimo.getMulta() );

        return emprestimoDTO;
    }

    private Long emprestimoLivroId(Emprestimo emprestimo) {
        if ( emprestimo == null ) {
            return null;
        }
        Livro livro = emprestimo.getLivro();
        if ( livro == null ) {
            return null;
        }
        Long id = livro.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String emprestimoLivroTitulo(Emprestimo emprestimo) {
        if ( emprestimo == null ) {
            return null;
        }
        Livro livro = emprestimo.getLivro();
        if ( livro == null ) {
            return null;
        }
        String titulo = livro.getTitulo();
        if ( titulo == null ) {
            return null;
        }
        return titulo;
    }

    private Long emprestimoClienteId(Emprestimo emprestimo) {
        if ( emprestimo == null ) {
            return null;
        }
        Cliente cliente = emprestimo.getCliente();
        if ( cliente == null ) {
            return null;
        }
        Long id = cliente.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String emprestimoClienteNome(Emprestimo emprestimo) {
        if ( emprestimo == null ) {
            return null;
        }
        Cliente cliente = emprestimo.getCliente();
        if ( cliente == null ) {
            return null;
        }
        String nome = cliente.getNome();
        if ( nome == null ) {
            return null;
        }
        return nome;
    }
}
