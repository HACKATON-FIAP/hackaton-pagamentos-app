package br.com.fiap.pagamentos.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class ServiceUnavailableResponse extends RuntimeException  {
    private final String message;
    public static final int CODE = 503;
}
