package org.example.exceptions;

public class FormaPagamentoNotFoundException extends RuntimeException {
    public FormaPagamentoNotFoundException(String message) {
        super(message);
    }
}
