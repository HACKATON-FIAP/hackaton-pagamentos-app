package br.com.fiap.pagamentos.api.response.exception;


public class BadRequestResponse extends RuntimeException {
    public BadRequestResponse(String mensagem) {
        super(mensagem);
    }
}
