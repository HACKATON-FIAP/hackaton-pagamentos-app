package br.com.fiap.pagamentos.api.model;

public class PagamentoDTODataFactory {

    public static PagamentoDTO criarPagamentoDTOSettersAndGetters() {
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setCpf("12345678901");
        pagamentoDTO.setNumero("1234567890123456");
        pagamentoDTO.setDataValidade("12/25");
        pagamentoDTO.setCvv("123");
        pagamentoDTO.setValor(1000);
        pagamentoDTO.setDescricao("Pagamento de teste");
        pagamentoDTO.setMetodoPagamento("Cartão de Crédito");
        pagamentoDTO.setStatus("Pendente");
        return pagamentoDTO;
    }

}
