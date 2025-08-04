package org.example.entities;

import jakarta.persistence.*;

@Entity
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CELULAR", nullable = false, length = 50)
    private String celular;

    @Column(name = "TELEFONE", nullable = false, length = 50)
    private String telefone;

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

