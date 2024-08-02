package br.com.fiap.pagamentos.api.response.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestResponse extends RuntimeException {
    private String message;
    private int statusCode = 400;
    public BadRequestResponse(String message) {
        this.message = message;
    }
}
