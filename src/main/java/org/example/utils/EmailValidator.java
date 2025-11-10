package org.example.utils;

import java.util.regex.Pattern;

/**
 * Utilitário de validação complementar para endereços de e-mail.
 */
public final class EmailValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^(?=.{1,254}$)(?=.{1,64}@)[A-Za-z0-9._%+-]+@" +
                    "(?:[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?\\.)+" +
                    "[A-Za-z]{2,}$"
    );

    private EmailValidator() {
    }

    /**
     * Normaliza um e-mail removendo espaços extras e convertendo para letras minúsculas.
     *
     * @param email e-mail informado
     * @return e-mail normalizado ou {@code null} caso o valor original seja nulo
     */
    public static String normalize(String email) {
        return email == null ? null : email.trim().toLowerCase();
    }

    /**
     * Realiza validações adicionais em um endereço de e-mail.
     *
     * @param email e-mail normalizado
     * @return {@code true} quando o e-mail passa pelas regras de validação, {@code false} caso contrário
     */
    public static boolean isValid(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            return false;
        }

        return !email.contains("..") && !email.startsWith(".") && !email.endsWith(".");
    }
}
