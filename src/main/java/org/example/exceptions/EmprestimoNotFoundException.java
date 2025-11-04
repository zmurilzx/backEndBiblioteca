package org.example.exceptions;

public class EmprestimoNotFoundException extends RuntimeException {
    public EmprestimoNotFoundException(String message) {
        super(message);
    }
}
