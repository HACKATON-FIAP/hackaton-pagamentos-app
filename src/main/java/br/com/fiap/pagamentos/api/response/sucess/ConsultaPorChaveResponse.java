package br.com.fiap.pagamentos.api.response.sucess;

import br.com.fiap.pagamentos.domain.model.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaPorChaveResponse {
    private double valor;
    private String descricao;
    private String metodoPagamento;
    private String status;

    public static List<ConsultaPorChaveResponse> fromPagamentos(List<Optional<Pagamento>> pagamentos) {
        return pagamentos.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(p -> new ConsultaPorChaveResponse(p.getValor(), p.getDescricao(), p.getMetodoPagamento(), p.getStatus()))
                .toList();
    }
}
