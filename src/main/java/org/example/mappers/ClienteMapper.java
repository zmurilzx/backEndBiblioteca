package org.example.mappers;

import org.example.dto.ClienteDTO;
import org.example.entities.Cliente;
import org.example.enums.Sexo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    // Mapeia DTO → Entidade, incluindo conversão de String para Enum (sexo)
    @Mapping(source = "sexo", target = "sexo", qualifiedByName = "mapSexo")
    Cliente toEntity(ClienteDTO dto);

    // Mapeia Entidade → DTO, incluindo conversão de Enum para String
    @Mapping(source = "sexo", target = "sexo", qualifiedByName = "mapSexoString")
    ClienteDTO toDTO(Cliente cliente);

    @Named("mapSexo")
    default Sexo mapSexo(String sexo) {
        if (sexo == null) return null;
        try {
            return Sexo.valueOf(sexo.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null; // evita crash se o valor for inválido
        }
    }

    @Named("mapSexoString")
    default String mapSexoString(Sexo sexo) {
        return sexo != null ? sexo.name() : null;
    }
}
