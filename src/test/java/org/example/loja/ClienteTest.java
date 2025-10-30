package org.example.loja;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void deveAceitarCpfValido() {
        Cliente cliente = new Cliente(1L, "Maria", "12345678901", "maria@example.com", "11999999999");
        assertEquals("12345678901", cliente.getCpf());
    }

    @Test
    void deveRejeitarCpfInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Cliente(1L, "João", "12345", "joao@example.com", "11999999999"));
    }

    @Test
    void deveAceitarEmailValido() {
        Cliente cliente = new Cliente(2L, "Ana", "10987654321", "ana.silva@dominio.com", "11888888888");
        assertEquals("ana.silva@dominio.com", cliente.getEmail());
    }

    @Test
    void deveRejeitarEmailInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Cliente(2L, "Ana", "10987654321", "anadominio.com", "11888888888"));
    }
}
