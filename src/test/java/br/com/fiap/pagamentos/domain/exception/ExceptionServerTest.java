package br.com.fiap.pagamentos.domain.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
class ExceptionServerTest {
    @Test
    void deveCriarUmaInternalServerErrorComMensagem() {
        var exception = ExceptionServerDataFactory.criarInternalServerError();
        Assertions.assertNotNull(exception);
    }

    @Test
    void deveCriarUmaServiceUnavailableComMensagem() {
        var exception = ExceptionServerDataFactory.criarServiceUnavailable();
        Assertions.assertNotNull(exception);
    }
}
