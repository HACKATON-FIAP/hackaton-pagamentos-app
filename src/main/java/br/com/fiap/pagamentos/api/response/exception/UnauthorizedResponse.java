package br.com.fiap.pagamentos.api.response.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;


@Builder
@AllArgsConstructor
public class UnauthorizedResponse extends RuntimeException {
    private final String message;
    public static final int CODE = 401;
}
