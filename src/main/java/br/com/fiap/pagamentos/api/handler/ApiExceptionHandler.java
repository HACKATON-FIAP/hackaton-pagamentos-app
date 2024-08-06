package br.com.fiap.pagamentos.api.handler;

import br.com.fiap.pagamentos.api.response.exception.BadRequestResponse;
import br.com.fiap.pagamentos.api.response.exception.NotFoundResponse;
import br.com.fiap.pagamentos.domain.exception.InternalServerErrorResponse;
import br.com.fiap.pagamentos.domain.exception.ServiceUnavailableResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(BadRequestResponse.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestResponse ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(NotFoundResponse.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundResponse ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(InternalServerErrorResponse.class)
    public ResponseEntity<Object> handleInternalServerError(InternalServerErrorResponse ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
    @ExceptionHandler(ServiceUnavailableResponse.class)
    public ResponseEntity<Object> handleServiceUnavailable(ServiceUnavailableResponse ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro inesperado: " + ex.getMessage());
    }
}