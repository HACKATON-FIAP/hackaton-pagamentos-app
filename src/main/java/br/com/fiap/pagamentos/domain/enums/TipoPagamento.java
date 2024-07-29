package br.com.fiap.pagamentos.domain.enums;
public enum TipoPagamento {
    CARTAO("cartão"),
    BOLETO("boleto"),
    DEBITO("débito"),
    PIX("pix");

    private final String descricao;

    TipoPagamento(String descricao) {
        this.descricao = descricao;
    }
}
