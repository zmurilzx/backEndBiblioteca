package org.example.mappers;

import org.example.dto.LivroDTO;
import org.example.entities.Livro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FornecedorMapper.class})
public interface LivroMapper {
    LivroDTO toDTO(Livro livro);
    Livro toEntity(LivroDTO dto);
}
