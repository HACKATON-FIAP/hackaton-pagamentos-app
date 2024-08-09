package br.com.fiap.pagamentos.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CartaoDTO {

    private Long id;

    private String cpf;

    private Long limite;

    private String numero;

    @JsonProperty("data_validade")
    private String dataValidade;

    private String cvv;

}
