package br.com.fiap.pagamentos.domain.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertNotNull;
class ExceptionServerTest {
    @Test
    void deveCriarUmaInternalServerErrorComMensagem() {
        var exception = ExceptionServerDataFactory.criarInternalServerError();
        assertNotNull(exception);
    }

    @Test
    void deveCriarUmaServiceUnavailableComMensagem() {
        var exception = ExceptionServerDataFactory.criarServiceUnavailable();
        assertNotNull(exception);
    }
}
