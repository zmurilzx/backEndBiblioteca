package org.example.exceptions;

public class LivroNotFoundException extends RuntimeException {
    public LivroNotFoundException(String message) {
        super(message);
    }
}