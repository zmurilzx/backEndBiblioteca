package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "O celular é obrigatório")
    @Pattern(regexp = "^\\d{10,11}$", message = "O celular deve conter 10 ou 11 dígitos")
    @Size(max = 50, message = "O celular deve ter no máximo 50 caracteres")
    @Column(name = "CELULAR", nullable = false, length = 50)
    private String celular;

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "^\\d{10,11}$", message = "O telefone deve conter 10 ou 11 dígitos")
    @Size(max = 50, message = "O telefone deve ter no máximo 50 caracteres")
    @Column(name = "TELEFONE", nullable = false, length = 50)
    private String telefone;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    public Contato() {
    }
    public Contato(Long id, String celular, String telefone, String email) {
        this.id = id;
        this.celular = celular;
        this.telefone = telefone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefone(String celular) {
        return celular;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}

