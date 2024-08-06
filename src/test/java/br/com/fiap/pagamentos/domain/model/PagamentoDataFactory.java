package br.com.fiap.pagamentos.domain.model;

public class PagamentoDataFactory {
    public static Pagamento criarPagamentoBuilder() {
        return Pagamento.builder()
                .chavePagamento(1L)
                .cpf("12345678901")
                .numero("1234567890123456")
                .dataValidade("12/25")
                .cvv("123")
                .valor(1000)
                .descricao("Pagamento de teste")
                .metodoPagamento("Cartão de Crédito")
                .status("Pendente")
                .build();
    }

    public static Pagamento criarPagamentoSettersAndGetters() {
        Pagamento pagamento = new Pagamento();
        pagamento.setChavePagamento(1L);
        pagamento.setCpf("12345678901");
        pagamento.setNumero("1234567890123456");
        pagamento.setDataValidade("12/25");
        pagamento.setCvv("123");
        pagamento.setValor(1000);
        pagamento.setDescricao("Pagamento de teste");
        pagamento.setMetodoPagamento("Cartão de Crédito");
        pagamento.setStatus("Pendente");
        return pagamento;
    }
}
