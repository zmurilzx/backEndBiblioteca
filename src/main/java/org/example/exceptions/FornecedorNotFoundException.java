package org.example.exceptions;

public class FornecedorNotFoundException extends RuntimeException {
    public FornecedorNotFoundException(String message) {
        super(message);
    }
}