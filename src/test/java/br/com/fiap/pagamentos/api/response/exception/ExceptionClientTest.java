package br.com.fiap.pagamentos.api.response.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExceptionClientTest {

    @Test
    void deveCriarUmaBadRequestErrorComMensagem() {
        var exception = ExceptionClientDataFactory.criarBadRequestResponse();
        Assertions.assertNotNull(exception);
    }

    @Test
    void deveCriarUmaNotFoundErrorComMensagem() {
        var exception = ExceptionClientDataFactory.criarNotFoundResponse();
        Assertions.assertNotNull(exception);
    }

}
