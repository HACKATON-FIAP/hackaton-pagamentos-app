package br.com.fiap.pagamentos.domain.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
class PagamentoTest {
    @Nested
    class modelEntityCriacao {
        @Test
        void deveCriarModelPagamentoBuilder() {
            Pagamento pagamento = PagamentoDataFactory.criarPagamentoBuilder();
            assertNotNull(pagamento);
            assertEquals(1L, pagamento.getChavePagamento());
            assertEquals("12345678901", pagamento.getCpf());
            assertEquals("1234567890123456", pagamento.getNumero());
            assertEquals("12/25", pagamento.getDataValidade());
            assertEquals("123", pagamento.getCvv());
            assertEquals(1000, pagamento.getValor());
            assertEquals("Pagamento de teste", pagamento.getDescricao());
            assertEquals("Cartão de Crédito", pagamento.getMetodoPagamento());
            assertEquals("Pendente", pagamento.getStatus());
        }

        @Test
        void deveCriarModelPagamentoSettersAndGetters() {
            Pagamento pagamento = PagamentoDataFactory.criarPagamentoSettersAndGetters();
            assertNotNull(pagamento);
            assertEquals("12345678901", pagamento.getCpf());
            assertEquals("1234567890123456", pagamento.getNumero());
            assertEquals("12/25", pagamento.getDataValidade());
            assertEquals("123", pagamento.getCvv());
            assertEquals(1000, pagamento.getValor());
            assertEquals("Pagamento de teste", pagamento.getDescricao());
            assertEquals("Cartão de Crédito", pagamento.getMetodoPagamento());
            assertEquals("Pendente", pagamento.getStatus());
        }
    }
}
