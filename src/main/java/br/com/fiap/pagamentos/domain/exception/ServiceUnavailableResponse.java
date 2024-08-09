package br.com.fiap.pagamentos.domain.exception;

public class ServiceUnavailableResponse extends RuntimeException  {
    public ServiceUnavailableResponse(String mensagem) {
        super(mensagem);
    }
}
