package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.dto.FormaPagamentoDTO;
import org.example.entities.FormaPagamento;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-24T03:55:58-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class FormaPagamentoMapperImpl implements FormaPagamentoMapper {

    @Override
    public FormaPagamentoDTO toDTO(FormaPagamento entity) {
        if ( entity == null ) {
            return null;
        }

        FormaPagamentoDTO formaPagamentoDTO = new FormaPagamentoDTO();

        formaPagamentoDTO.setId( entity.getId() );
        formaPagamentoDTO.setTipo( entity.getTipo() );
        formaPagamentoDTO.setDiasEntreParcelas( entity.getDiasEntreParcelas() );

        return formaPagamentoDTO;
    }

    @Override
    public FormaPagamento toEntity(FormaPagamentoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FormaPagamento formaPagamento = new FormaPagamento();

        formaPagamento.setId( dto.getId() );
        formaPagamento.setTipo( dto.getTipo() );
        formaPagamento.setDiasEntreParcelas( dto.getDiasEntreParcelas() );

        return formaPagamento;
    }
}
