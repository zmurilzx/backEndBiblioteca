package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.dto.ClienteDTO;
import org.example.entities.Cliente;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-27T15:38:57-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.16 (Microsoft)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente toEntity(ClienteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setSexo( mapSexo( dto.getSexo() ) );
        cliente.setNome( dto.getNome() );
        cliente.setCpf( dto.getCpf() );
        cliente.setRg( dto.getRg() );
        cliente.setDataNascimento( dto.getDataNascimento() );
        cliente.setObservacoes( dto.getObservacoes() );
        cliente.setAtivo( dto.getAtivo() );
        cliente.setId( dto.getId() );

        return cliente;
    }

    @Override
    public ClienteDTO toDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setSexo( mapSexoString( cliente.getSexo() ) );
        clienteDTO.setId( cliente.getId() );
        clienteDTO.setNome( cliente.getNome() );
        clienteDTO.setCpf( cliente.getCpf() );
        clienteDTO.setRg( cliente.getRg() );
        clienteDTO.setDataNascimento( cliente.getDataNascimento() );
        clienteDTO.setAtivo( cliente.getAtivo() );
        clienteDTO.setObservacoes( cliente.getObservacoes() );

        return clienteDTO;
    }
}
