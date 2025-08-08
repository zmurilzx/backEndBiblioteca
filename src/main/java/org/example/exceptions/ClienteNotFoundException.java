package org.example.exceptions;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String mensagem) {
        super(mensagem);
    }
}
