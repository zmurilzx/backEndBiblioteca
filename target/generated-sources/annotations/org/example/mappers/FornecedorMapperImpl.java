package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.dto.FornecedorDTO;
import org.example.entities.Fornecedor;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-23T14:00:27-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class FornecedorMapperImpl implements FornecedorMapper {

    @Override
    public FornecedorDTO toDTO(Fornecedor fornecedor) {
        if ( fornecedor == null ) {
            return null;
        }

        FornecedorDTO fornecedorDTO = new FornecedorDTO();

        fornecedorDTO.setId( fornecedor.getId() );
        fornecedorDTO.setRazaoSocial( fornecedor.getRazaoSocial() );
        fornecedorDTO.setNomeFantasia( fornecedor.getNomeFantasia() );
        fornecedorDTO.setCnpj( fornecedor.getCnpj() );

        return fornecedorDTO;
    }

    @Override
    public Fornecedor toEntity(FornecedorDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setId( dto.getId() );
        fornecedor.setRazaoSocial( dto.getRazaoSocial() );
        fornecedor.setNomeFantasia( dto.getNomeFantasia() );
        fornecedor.setCnpj( dto.getCnpj() );

        return fornecedor;
    }
}
