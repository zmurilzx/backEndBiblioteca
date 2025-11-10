package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.dto.FornecedorDTO;
import org.example.entities.Fornecedor;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-06T14:22:51-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Microsoft)"
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
        fornecedorDTO.setTelefone( fornecedor.getTelefone() );
        fornecedorDTO.setEndereco( fornecedor.getEndereco() );
        fornecedorDTO.setInscricaoEstadual( fornecedor.getInscricaoEstadual() );
        fornecedorDTO.setInscricaoMunicipal( fornecedor.getInscricaoMunicipal() );
        fornecedorDTO.setContatoResponsavel( fornecedor.getContatoResponsavel() );
        fornecedorDTO.setAtivo( fornecedor.getAtivo() );
        fornecedorDTO.setObservacoes( fornecedor.getObservacoes() );
        fornecedorDTO.setDataCadastro( fornecedor.getDataCadastro() );

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
        fornecedor.setInscricaoEstadual( dto.getInscricaoEstadual() );
        fornecedor.setInscricaoMunicipal( dto.getInscricaoMunicipal() );
        fornecedor.setContatoResponsavel( dto.getContatoResponsavel() );
        fornecedor.setTelefone( dto.getTelefone() );
        fornecedor.setEndereco( dto.getEndereco() );
        fornecedor.setDataCadastro( dto.getDataCadastro() );
        fornecedor.setAtivo( dto.getAtivo() );
        fornecedor.setObservacoes( dto.getObservacoes() );

        return fornecedor;
    }

    @Override
    public void updateEntityFromDto(FornecedorDTO dto, Fornecedor fornecedor) {
        if ( dto == null ) {
            return;
        }

        fornecedor.setRazaoSocial( dto.getRazaoSocial() );
        fornecedor.setNomeFantasia( dto.getNomeFantasia() );
        fornecedor.setCnpj( dto.getCnpj() );
        fornecedor.setInscricaoEstadual( dto.getInscricaoEstadual() );
        fornecedor.setInscricaoMunicipal( dto.getInscricaoMunicipal() );
        fornecedor.setContatoResponsavel( dto.getContatoResponsavel() );
        fornecedor.setTelefone( dto.getTelefone() );
        fornecedor.setEndereco( dto.getEndereco() );
        fornecedor.setDataCadastro( dto.getDataCadastro() );
        fornecedor.setAtivo( dto.getAtivo() );
        fornecedor.setObservacoes( dto.getObservacoes() );
    }
}
