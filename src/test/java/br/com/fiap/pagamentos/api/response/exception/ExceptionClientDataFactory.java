package br.com.fiap.pagamentos.api.response.exception;

public class ExceptionClientDataFactory {

    public static BadRequestResponse criarBadRequestResponse() {
        return BadRequestResponse.builder()
                .message("Requisição inválida. Verifique os dados enviados e tente novamente.")
                .build();
    }
    public static NotFoundResponse criarNotFoundResponse() {
        return NotFoundResponse.builder()
                .message("Recurso não encontrado. Verifique se o recurso solicitado existe.")
                .build();
    }
    public static UnauthorizedResponse criarUnauthorizedResponse() {
        return UnauthorizedResponse.builder()
                .message("Acesso não autorizado. Verifique suas credenciais de autenticação.")
                .build();
    }
}
