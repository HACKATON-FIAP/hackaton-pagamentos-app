package br.com.fiap.pagamentos.api.controller;

import br.com.fiap.pagamentos.api.model.PagamentoDTO;
import br.com.fiap.pagamentos.api.response.exception.BadRequestResponse;
import br.com.fiap.pagamentos.api.response.exception.NotFoundResponse;
import br.com.fiap.pagamentos.api.response.sucess.ConsultaPorChaveResponse;
import br.com.fiap.pagamentos.domain.exception.InternalServerErrorResponse;
import br.com.fiap.pagamentos.domain.model.Pagamento;
import br.com.fiap.pagamentos.domain.service.PagamentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/pagamentos")
@AllArgsConstructor
public class PagamentoController {

    private static final Logger logger = Logger.getLogger(PagamentoService.class.getName());
    private PagamentoService pagamentoService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity registrarPagamento(@Valid @RequestBody PagamentoDTO dto, BindingResult result) throws InternalServerErrorResponse {

        logger.info("Recebendo solicitação para registrar pagamento do cliente");
        if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Erro de validação: ");
            result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("; "));
            logger.warning("Erro ao registrar pagamento: " + errorMessage.toString());
            throw new BadRequestResponse("Erro ao registrar pagamento: " + errorMessage.toString());
        }

        Pagamento p = pagamentoService.registrarPagamento(dto);
        logger.info("Pagamento registrado com sucesso: " + p.getChave_pagamento());
        return ResponseEntity.ok(p.getChave_pagamento());
    }

    @GetMapping("/cliente/{chave}")
    public ResponseEntity consultarPagamentoCliente(@PathVariable Long chave) {

        logger.info("Recebendo solicitação para consultar pagamento do cliente com a chave: " + chave);
        Optional<Pagamento> pagamento = pagamentoService.consultarPagamentoCliente(chave);

        if (pagamento.isPresent()) {
            Pagamento p = pagamento.get();
            ConsultaPorChaveResponse response = ConsultaPorChaveResponse.builder()
                .valor(p.getValor())
                .descricao(p.getDescricao())
                .metodoPagamento(p.getMetodo_pagamento())
                .status(p.getStatus())
                .build();

            logger.info("Pagamento encontrado: " + response);
            return ResponseEntity.ok(response);
        } else {
            logger.warning("Pagamento não encontrado para a chave: " + chave);
            throw new NotFoundResponse("Pagamento não encontrado para a chave: " + chave);
        }
    }

}



