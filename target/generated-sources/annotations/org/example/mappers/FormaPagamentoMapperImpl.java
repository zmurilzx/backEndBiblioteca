package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.dto.FormaPagamentoDTO;
import org.example.entities.FormaPagamento;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-06T14:22:51-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Microsoft)"
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
        formaPagamentoDTO.setDescricao( entity.getDescricao() );
        formaPagamentoDTO.setTipo( entity.getTipo() );
        formaPagamentoDTO.setNumeroParcelas( entity.getNumeroParcelas() );
        formaPagamentoDTO.setDiasEntreParcelas( entity.getDiasEntreParcelas() );
        formaPagamentoDTO.setPermiteTroco( entity.isPermiteTroco() );
        formaPagamentoDTO.setTaxaPercentual( entity.getTaxaPercentual() );
        formaPagamentoDTO.setAtivo( entity.isAtivo() );

        return formaPagamentoDTO;
    }

    @Override
    public FormaPagamento toEntity(FormaPagamentoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FormaPagamento formaPagamento = new FormaPagamento();

        formaPagamento.setId( dto.getId() );
        formaPagamento.setDescricao( dto.getDescricao() );
        formaPagamento.setTipo( dto.getTipo() );
        formaPagamento.setNumeroParcelas( dto.getNumeroParcelas() );
        formaPagamento.setDiasEntreParcelas( dto.getDiasEntreParcelas() );
        if ( dto.getPermiteTroco() != null ) {
            formaPagamento.setPermiteTroco( dto.getPermiteTroco() );
        }
        formaPagamento.setTaxaPercentual( dto.getTaxaPercentual() );
        if ( dto.getAtivo() != null ) {
            formaPagamento.setAtivo( dto.getAtivo() );
        }

        return formaPagamento;
    }
}
