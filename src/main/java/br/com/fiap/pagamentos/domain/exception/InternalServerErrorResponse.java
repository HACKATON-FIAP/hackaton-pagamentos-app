package br.com.fiap.pagamentos.domain.exception;

public class InternalServerErrorResponse extends RuntimeException  {
    public InternalServerErrorResponse(String mensagem) {
        super(mensagem);
    }
}
