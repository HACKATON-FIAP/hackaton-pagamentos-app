package br.com.fiap.pagamentos.api.model;

import br.com.fiap.pagamentos.domain.enums.TipoPagamento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDTO {

    @NotNull(message = "O ID do carrinho não pode ser nulo")
    public Long idCarrinho;

    @NotNull(message = "O valor total não pode ser nulo")
    @Positive(message = "O valor total deve ser maior que zero")
    public Double valorTotal;

    @NotNull(message = "O tipo de pagamento não pode ser nulo")
    @Enumerated(EnumType.STRING)
    public TipoPagamento tipoPagamento;

}

