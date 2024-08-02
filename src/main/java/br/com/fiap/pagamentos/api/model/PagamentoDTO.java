package br.com.fiap.pagamentos.api.model;

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

    @NotNull(message = "O cpf do cliente não pode ser nulo")
    private String cpf;

    @NotNull(message = "O numero do cartão não pode ser nulo")
    private String numero;

    @NotNull(message = "A data de validade do cartão está incorreta")
    private String data_validade;

    @NotNull(message = "O cvv do cartão está incorreto")
    private String cvv;

    @NotNull(message = "O valor total não pode ser nulo")
    @Positive(message = "O valor total deve ser maior que zero")
    private Integer valor;

    private String descricao = "Compra de produto X";
    private String metodo_pagamento = "cartao_credito";
    private String status = "aprovado";
}

