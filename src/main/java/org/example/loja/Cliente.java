package org.example.loja;

import java.util.Objects;
import java.util.regex.Pattern;

public class Cliente {
    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{11}");

    private final long id;
    private final String nome;
    private final String cpf;
    private final String email;
    private final String telefone;

    public Cliente(long id, String nome, String cpf, String email, String telefone) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id do cliente deve ser positivo");
        }
        this.id = id;
        this.nome = Objects.requireNonNull(nome, "nome");
        this.cpf = validarCpf(cpf);
        this.email = validarEmail(email);
        this.telefone = Objects.requireNonNull(telefone, "telefone");
    }

    private String validarCpf(String cpf) {
        Objects.requireNonNull(cpf, "cpf");
        if (!CPF_PATTERN.matcher(cpf).matches()) {
            throw new IllegalArgumentException("CPF inválido");
        }
        return cpf;
    }

    private String validarEmail(String email) {
        Objects.requireNonNull(email, "email");
        int atIndex = email.indexOf('@');
        if (atIndex <= 0 || atIndex == email.length() - 1) {
            throw new IllegalArgumentException("Email inválido");
        }
        String dominio = email.substring(atIndex + 1);
        if (!dominio.contains(".")) {
            throw new IllegalArgumentException("Email inválido");
        }
        return email;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }
}
