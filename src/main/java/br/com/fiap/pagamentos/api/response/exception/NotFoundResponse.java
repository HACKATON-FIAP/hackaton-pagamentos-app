package br.com.fiap.pagamentos.api.response.exception;;

public class NotFoundResponse extends RuntimeException {
    public NotFoundResponse(String mensagem) {
        super(mensagem);
    }
}
