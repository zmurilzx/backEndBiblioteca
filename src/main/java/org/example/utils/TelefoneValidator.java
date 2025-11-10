package org.example.utils;

import java.util.regex.Pattern;

/**
 * Utilitário responsável por sanitizar e validar números de telefone fixo ou celular brasileiros.
 */
public final class TelefoneValidator {

    private static final Pattern VALID_LENGTH_PATTERN = Pattern.compile("^\\d{10,11}$");

    private TelefoneValidator() {
    }

    /**
     * Remove qualquer caractere que não seja numérico.
     *
     * @param telefone valor original informado pelo usuário
     * @return sequência contendo apenas dígitos ou {@code null} quando o telefone não tiver sido informado
     */
    public static String sanitize(String telefone) {
        return telefone == null ? null : telefone.replaceAll("\\D", "");
    }

    /**
     * Realiza validação básica do telefone sanitizado.
     *
     * @param telefoneSanitizado telefone contendo apenas dígitos
     * @return {@code true} quando o telefone possui 10 ou 11 dígitos e não é composto por dígitos repetidos
     */
    public static boolean isValid(String telefoneSanitizado) {
        if (telefoneSanitizado == null || telefoneSanitizado.isBlank()) {
            return false;
        }

        if (!VALID_LENGTH_PATTERN.matcher(telefoneSanitizado).matches()) {
            return false;
        }

        return !telefoneSanitizado.chars().allMatch(ch -> ch == telefoneSanitizado.charAt(0));
    }
}
