package br.com.fiap.pagamentos.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDTO {
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("numero")
    private String numero;
    @JsonProperty("data_validade")
    private String dataValidade;
    @JsonProperty("cvv")
    private String cvv;
    @JsonProperty("valor")
    private Integer valor;

    private String descricao = "Compra de produto X";
    private String metodoPagamento = "cartao_credito";
    private String status = "aprovado";
}

