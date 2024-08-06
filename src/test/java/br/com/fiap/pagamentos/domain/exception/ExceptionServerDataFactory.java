package br.com.fiap.pagamentos.domain.exception;

public class ExceptionServerDataFactory {
    public static InternalServerErrorResponse criarInternalServerError() {
        return InternalServerErrorResponse.builder()
                .message("Ocorreu um erro inesperado em nosso servidor. Nossa equipe técnica já está trabalhando para resolver o problema. Por favor, tente novamente mais tarde.")
                .build();
    }
    public static ServiceUnavailableResponse criarServiceUnavailable() {
        return ServiceUnavailableResponse.builder()
                .message("Nossos sistemas estão temporariamente indisponíveis no momento. Por favor, tente novamente mais tarde.")
                .build();
    }
}
