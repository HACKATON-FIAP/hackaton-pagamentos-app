package br.com.fiap.pagamentos.api.response.sucess;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaPorChaveResponse {
    private double valor;
    private String descricao;
    private String metodoPagamento;
    private String status;
}
