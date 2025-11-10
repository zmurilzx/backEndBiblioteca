package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.dto.LivroDTO;
import org.example.entities.Livro;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-06T14:22:51-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Microsoft)"
)
@Component
public class LivroMapperImpl implements LivroMapper {

    @Override
    public LivroDTO toDTO(Livro livro) {
        if ( livro == null ) {
            return null;
        }

        LivroDTO livroDTO = new LivroDTO();

        livroDTO.setId( livro.getId() );
        livroDTO.setTitulo( livro.getTitulo() );
        livroDTO.setAutor( livro.getAutor() );
        livroDTO.setIsbn( livro.getIsbn() );
        livroDTO.setEstoque( livro.getEstoque() );

        return livroDTO;
    }

    @Override
    public Livro toEntity(LivroDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Livro livro = new Livro();

        livro.setId( dto.getId() );
        livro.setTitulo( dto.getTitulo() );
        livro.setAutor( dto.getAutor() );
        livro.setIsbn( dto.getIsbn() );
        livro.setEstoque( dto.getEstoque() );

        return livro;
    }
}
