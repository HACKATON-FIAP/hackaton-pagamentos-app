package br.com.fiap.pagamentos.api.model;

public class CartaoDTODataFactory {

    public static CartaoDTO criarCartaoDTOSettersAndGetters() {
        CartaoDTO cartaoDTO = new CartaoDTO();
        cartaoDTO.setId(1L);
        cartaoDTO.setCpf("12345678901");
        cartaoDTO.setNumero("1234567890123456");
        cartaoDTO.setDataValidade("12/25");
        cartaoDTO.setCvv("123");
        cartaoDTO.setLimite(1000L);
        return cartaoDTO;
    }
}
