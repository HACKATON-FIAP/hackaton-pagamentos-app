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
        assertEquals("Pagamento de teste Builder", response.getDescricao());
        assertEquals("Cartão de Crédito Builder", response.getMetodoPagamento());
        assertEquals("Pendente Builder", response.getStatus());
    }
    @Test
    void deveCriarUmaConsultaPorChaveResponseSettersAndGetters() {
        var response = ConsultaPorChaveDataFactory.criarConsultaPorChaveResponseSettersAndGetters();
        Assertions.assertNotNull(response);
        assertEquals(1000, response.getValor());
        assertEquals("Pagamento de teste SettersAndGetters", response.getDescricao());
        assertEquals("Cartão de Crédito SettersAndGetters", response.getMetodoPagamento());
        assertEquals("Pendente SettersAndGetters", response.getStatus());
    }

    @Test
    void deveCriarUmaConsultaPorChaveResponseConstructor() {
        var response = ConsultaPorChaveDataFactory.criarConsultaPorChaveResponseConstructor();
        Assertions.assertNotNull(response);
        assertEquals(1000, response.getValor());
        assertEquals("Pagamento de teste Constructor", response.getDescricao());
        assertEquals("Cartão de Crédito Constructor", response.getMetodoPagamento());
        assertEquals("Pendente Constructor", response.getStatus());
    }

}
