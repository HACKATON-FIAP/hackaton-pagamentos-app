package br.com.fiap.pagamentos.api.model;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PagamentoDTOTest {
    @Nested
    class modelDT0Criacao {

        @Test
        void deveCriarModelPagamentoDTOSettersAndGetters() {
            PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
            assertNotNull(pagamentoDTO);
            assertEquals("12345678901", pagamentoDTO.getCpf());
            assertEquals("1234567890123456", pagamentoDTO.getNumero());
            assertEquals("12/25", pagamentoDTO.getDataValidade());
            assertEquals("123", pagamentoDTO.getCvv());
            assertEquals(1000, pagamentoDTO.getValor());
            assertEquals("Pagamento de teste", pagamentoDTO.getDescricao());
            assertEquals("Cartão de Crédito", pagamentoDTO.getMetodoPagamento());
            assertEquals("Pendente", pagamentoDTO.getStatus());
        }
    }
}
