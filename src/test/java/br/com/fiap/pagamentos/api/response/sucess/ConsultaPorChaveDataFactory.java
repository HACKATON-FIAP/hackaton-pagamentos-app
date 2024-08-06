package br.com.fiap.pagamentos.api.response.sucess;

public class ConsultaPorChaveDataFactory {

    public static ConsultaPorChaveResponse criarConsultaPorChaveResponseConstructor() {
        ConsultaPorChaveResponse consultaPorChaveResponse = new ConsultaPorChaveResponse(1000,"Pagamento de teste Constructor", "Cartão de Crédito Constructor","Pendente Constructor");
        return consultaPorChaveResponse;
    }
    public static ConsultaPorChaveResponse criarConsultaPorChaveResponseBuilder() {
        return ConsultaPorChaveResponse.builder()
                .valor(1000)
                .descricao("Pagamento de teste Builder")
                .metodoPagamento("Cartão de Crédito Builder")
                .status("Pendente Builder")
                .build();
    }

    public static ConsultaPorChaveResponse criarConsultaPorChaveResponseSettersAndGetters() {
        ConsultaPorChaveResponse consultaPorChaveResponse = new ConsultaPorChaveResponse();
        consultaPorChaveResponse.setValor(1000);
        consultaPorChaveResponse.setDescricao("Pagamento de teste SettersAndGetters");
        consultaPorChaveResponse.setMetodoPagamento("Cartão de Crédito SettersAndGetters");
        consultaPorChaveResponse.setStatus("Pendente SettersAndGetters");
        return consultaPorChaveResponse;
    }
}
