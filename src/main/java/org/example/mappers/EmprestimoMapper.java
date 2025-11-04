package org.example.mappers;

import org.example.dto.EmprestimoDTO;
import org.example.entities.Emprestimo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmprestimoMapper {

    @Mapping(source = "livro.id", target = "livroId")
    @Mapping(source = "livro.titulo", target = "livroTitulo")
    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.nome", target = "clienteNome")
    EmprestimoDTO toDTO(Emprestimo emprestimo);
}
