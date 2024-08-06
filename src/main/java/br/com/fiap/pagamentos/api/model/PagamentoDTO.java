package br.com.fiap.pagamentos.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDTO {
    private String cpf;
    private String numero;
    private String dataValidade;
    private String cvv;
    private Integer valor;
    private String descricao = "Compra de produto X";
    private String metodoPagamento = "cartao_credito";
    private String status = "aprovado";
}

