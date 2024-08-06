package br.com.fiap.pagamentos.api.response.exception;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;
class ExceptionClientTest {

    @Test
    void deveCriarUmaBadRequestErrorComMensagem() {
        var exception = ExceptionClientDataFactory.criarBadRequestResponse();
        assertNotNull(exception);
    }

    @Test
    void deveCriarUmaNotFoundErrorComMensagem() {
        var exception = ExceptionClientDataFactory.criarNotFoundResponse();
        assertNotNull(exception);
    }

    @Test
    void deveCriarUmaUnauthorizedErrorComMensagem() {
        var exception = ExceptionClientDataFactory.criarUnauthorizedResponse();
        assertNotNull(exception);
    }

}
