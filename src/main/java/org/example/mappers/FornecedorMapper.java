package org.example.mappers;

import org.example.dto.FornecedorDTO;
import org.example.dto.LivroDTO;
import org.example.entities.Fornecedor;
import org.example.entities.Livro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FornecedorMapper {
    FornecedorDTO toDTO(Fornecedor fornecedor);
    Fornecedor toEntity(FornecedorDTO dto);
}


