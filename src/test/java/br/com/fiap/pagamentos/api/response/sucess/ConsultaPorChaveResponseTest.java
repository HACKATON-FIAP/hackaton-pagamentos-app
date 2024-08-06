package br.com.fiap.pagamentos.api.response.sucess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsultaPorChaveResponseTest {

    @Test
    void deveCriarUmaConsultaPorChaveResponseBuilder() {
        var response = ConsultaPorChaveDataFactory.criarConsultaPorChaveResponseBuilder();
        Assertions.assertNotNull(response);
        assertEquals(1000, response.getValor());
        assertEquals("Pagamento de teste", response.getDescricao());
        assertEquals("Cartão de Crédito", response.getMetodoPagamento());
        assertEquals("Pendente", response.getStatus());
    }
    @Test
    void deveCriarUmaConsultaPorChaveResponseSettersAndGetters() {
        var response = ConsultaPorChaveDataFactory.criarConsultaPorChaveResponseBuilder();
        assertNotNull(response);
        assertEquals(1000, response.getValor());
        assertEquals("Pagamento de teste", response.getDescricao());
        assertEquals("Cartão de Crédito", response.getMetodoPagamento());
        assertEquals("Pendente", response.getStatus());
    }

}
