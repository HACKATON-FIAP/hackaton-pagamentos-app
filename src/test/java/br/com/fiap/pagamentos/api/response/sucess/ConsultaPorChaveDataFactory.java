package br.com.fiap.pagamentos.api.response.sucess;

public class ConsultaPorChaveDataFactory {
    public static ConsultaPorChaveResponse criarConsultaPorChaveResponseBuilder() {
        return ConsultaPorChaveResponse.builder()
                .valor(1000)
                .descricao("Pagamento de teste")
                .metodoPagamento("Cartão de Crédito")
                .status("Pendente")
                .build();
    }

    public static ConsultaPorChaveResponse criarConsultaPorChaveResponseSettersAndGetters() {
        ConsultaPorChaveResponse consultaPorChaveResponse = new ConsultaPorChaveResponse();
        consultaPorChaveResponse.setValor(1000);
        consultaPorChaveResponse.setDescricao("Pagamento de teste");
        consultaPorChaveResponse.setMetodoPagamento("Cartão de Crédito");
        consultaPorChaveResponse.setStatus("Pendente");
        return consultaPorChaveResponse;
    }
}
