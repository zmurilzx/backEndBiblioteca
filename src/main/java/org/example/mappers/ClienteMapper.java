package org.example.mappers;

import org.example.dto.ClienteDTO;
import org.example.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteDTO toDTO(Cliente cliente);
    Cliente toEntity(ClienteDTO dto);
}
