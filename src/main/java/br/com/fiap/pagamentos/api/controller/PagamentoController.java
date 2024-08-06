package br.com.fiap.pagamentos.api.controller;

import br.com.fiap.pagamentos.api.model.PagamentoDTO;
import br.com.fiap.pagamentos.api.response.exception.BadRequestResponse;
import br.com.fiap.pagamentos.api.response.exception.NotFoundResponse;
import br.com.fiap.pagamentos.api.response.sucess.ConsultaPorChaveResponse;
import br.com.fiap.pagamentos.domain.model.Pagamento;
import br.com.fiap.pagamentos.domain.service.PagamentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pagamentos")
public class PagamentoController {

    private static final Logger logger = Logger.getLogger(PagamentoController.class.getName());
    private final  PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<String> registrarPagamento(@RequestBody PagamentoDTO dto) {
        try {
            if(dto.getCpf() == null){
                throw new BadRequestResponse("O cpf do cliente não pode ser nulo");
            }
            if(dto.getNumero() == null || !Pattern.matches("\\d{4}(\\s?\\d{4}){3}", dto.getNumero())){
                throw new BadRequestResponse("O numero do cartão não pode ser nulo e deve conter apenas números");
            }
            if(dto.getDataValidade() == null){
                throw new BadRequestResponse("A data de validade do cartão não pode ser nulo");
            }
            if(dto.getCvv() == null || !Pattern.matches("\\d+", dto.getCvv())){
                throw new BadRequestResponse("O cvv do cartão não pode ser nulo e deve conter apenas números");
            }
            if(dto.getValor() == null || dto.getValor() < 0){
                throw new BadRequestResponse("O valor da compra está nulo ou incorreto");
            }

            Pagamento pagamento = pagamentoService.registrarPagamento(dto);
            logger.info("Pagamento registrado com sucesso");
            return ResponseEntity.ok("chave_pagamento: "+ pagamento.getChavePagamento());
        } catch (BadRequestResponse e) {
            logger.warning("Erro de validação: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.severe("Erro interno: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/cliente/{chave}")
    public ResponseEntity<List<ConsultaPorChaveResponse>> consultarPagamentoCliente(@PathVariable String chave) {

        chave.replaceAll("\\D", "");

        if(chave.isEmpty()){
            throw new BadRequestResponse("O cpf do cliente não pode ser vazio");
        }
        logger.info("Recebendo solicitação para consultar pagamento do cliente com a chave");
        List<Optional<Pagamento>> pagamentos = pagamentoService.consultarPagamentoCliente(chave);

        if (pagamentos.isEmpty()) {
            logger.warning("Pagamento não encontrado para a chave");
            throw new NotFoundResponse("Pagamento não encontrado para a chave: " + chave);
        } else {
            logger.info("Pagamento encontrado:");
            return ResponseEntity.ok(ConsultaPorChaveResponse.fromPagamentos(pagamentos));
        }
    }

}



