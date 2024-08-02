package br.com.fiap.pagamentos.api.response.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnauthorizedResponse extends RuntimeException {
    private String message;
    private int statusCode = 401;
    public UnauthorizedResponse(String message) {
        this.message = message;
    }
}
