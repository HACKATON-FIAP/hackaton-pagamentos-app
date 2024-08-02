package br.com.fiap.pagamentos.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceUnavailableResponse extends RuntimeException  {
    private String message;
    private int statusCode = 503;

    public ServiceUnavailableResponse(String message) {
        this.message = message;
    }
}
