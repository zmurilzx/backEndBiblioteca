package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.example.enums.Sexo;

import java.util.Date;

public class ClienteDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotNull(message = "O sexo é obrigatório")
    private Sexo sexo;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido (formato: 000.000.000-00)")
    private String cpf;

    @NotBlank(message = "O RG é obrigatório")
    @Size(max = 9, message = "O RG deve ter no máximo 9 caracteres")
    private String rg;

    @Past(message = "A data de nascimento deve ser no passado")
    private Date dataNascimento;

    private Date dataCadastro;

    @Size(max = 20, message = "As observações devem ter no máximo 20 caracteres")
    private String observacoes;

    private Boolean ativo;

    // Getters e Setters
}
