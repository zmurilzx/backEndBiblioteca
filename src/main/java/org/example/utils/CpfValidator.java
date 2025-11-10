package org.example.utils;

/**
 * Utilitário para validação de CPF seguindo as regras oficiais de cálculo dos dígitos verificadores.
 */
public final class CpfValidator {

    private CpfValidator() {
    }

    /**
     * Remove todos os caracteres que não sejam dígitos de um CPF.
     *
     * @param cpf valor informado
     * @return CPF contendo apenas números ou {@code null} quando o valor original for nulo
     */
    public static String sanitize(String cpf) {
        return cpf == null ? null : cpf.replaceAll("\\D", "");
    }

    /**
     * Verifica se um CPF é válido calculando seus dígitos verificadores.
     *
     * @param cpf CPF contendo apenas números
     * @return {@code true} quando o CPF é válido, {@code false} caso contrário
     */
    public static boolean isValid(String cpf) {
        if (cpf == null) {
            return false;
        }

        String digits = sanitize(cpf);
        if (digits == null || digits.length() != 11) {
            return false;
        }

        if (isSequence(digits)) {
            return false;
        }

        int firstVerifier = calculateDigit(digits, 10);
        int secondVerifier = calculateDigit(digits, 11);

        return firstVerifier == Character.getNumericValue(digits.charAt(9))
                && secondVerifier == Character.getNumericValue(digits.charAt(10));
    }

    private static boolean isSequence(String digits) {
        char first = digits.charAt(0);
        for (int i = 1; i < digits.length(); i++) {
            if (digits.charAt(i) != first) {
                return false;
            }
        }
        return true;
    }

    private static int calculateDigit(String digits, int weight) {
        int sum = 0;
        for (int i = 0; i < weight - 1; i++) {
            sum += Character.getNumericValue(digits.charAt(i)) * (weight - i);
        }

        int remainder = (sum * 10) % 11;
        return remainder == 10 ? 0 : remainder;
    }
}
