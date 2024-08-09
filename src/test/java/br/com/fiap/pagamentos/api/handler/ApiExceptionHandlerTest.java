package br.com.fiap.pagamentos.api.handler;

import br.com.fiap.pagamentos.api.response.exception.BadRequestResponse;
import br.com.fiap.pagamentos.api.response.exception.NotFoundResponse;
import br.com.fiap.pagamentos.domain.exception.InternalServerErrorResponse;
import br.com.fiap.pagamentos.domain.exception.ServiceUnavailableResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

 class ApiExceptionHandlerTest {

    @Test
     void deveRetornarBadRequestParaBadRequestException() {
        ApiExceptionHandler handler = new ApiExceptionHandler();

        String message = "Dados inválidos";
        BadRequestResponse exception = new BadRequestResponse(message);

        ResponseEntity<Object> response = handler.handleBadRequestException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(message, response.getBody());
    }

    @Test
    void deveRetornarInternalServerErrorParaInternalServerErrorException() {
        ApiExceptionHandler handler = new ApiExceptionHandler();

        String message = "Erro interno no servidor";
        InternalServerErrorResponse exception = new InternalServerErrorResponse(message);

        ResponseEntity<Object> response = handler.handleInternalServerError(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(message, response.getBody());
    }

    @Test
    void deveRetornarNotFoundParaNotFoundException() {
        ApiExceptionHandler handler = new ApiExceptionHandler();

        String message = "Recurso não encontrado";
        NotFoundResponse exception = new NotFoundResponse(message);

        ResponseEntity<Object> response = handler.handleNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(message, response.getBody());
    }

    @Test
    void deveRetornarServiceUnavailableParaServiceUnavailableException() {
        ApiExceptionHandler handler = new ApiExceptionHandler();

        String message = "Serviço indisponível";
        ServiceUnavailableResponse exception = new ServiceUnavailableResponse(message);

        ResponseEntity<Object> response = handler.handleServiceUnavailable(exception);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        assertEquals(message, response.getBody());
    }
}
