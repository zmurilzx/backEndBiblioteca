package org.example.mappers;

import org.example.dto.FornecedorDTO;
import org.example.entities.Fornecedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FornecedorMapper {
    FornecedorDTO toDTO(Fornecedor fornecedor);
    Fornecedor toEntity(FornecedorDTO dto);
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(FornecedorDTO dto, @MappingTarget Fornecedor fornecedor);
}
