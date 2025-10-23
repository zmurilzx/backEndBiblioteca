package org.example.mappers;

import org.example.dto.FormaPagamentoDTO;
import org.example.entities.FormaPagamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FormaPagamentoMapper {

    FormaPagamentoDTO toDTO(FormaPagamento entity);

    FormaPagamento toEntity(FormaPagamentoDTO dto);
}
