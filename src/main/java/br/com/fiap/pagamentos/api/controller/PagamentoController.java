package br.com.fiap.pagamentos.api.controller;

import br.com.fiap.pagamentos.api.model.PagamentoDTO;
import br.com.fiap.pagamentos.api.response.exception.BadRequestResponse;
import br.com.fiap.pagamentos.api.response.exception.NotFoundResponse;
import br.com.fiap.pagamentos.api.response.sucess.ConsultaPorChaveResponse;
import br.com.fiap.pagamentos.domain.model.Pagamento;
import br.com.fiap.pagamentos.domain.service.PagamentoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pagamentos")
@CrossOrigin("*")
public class PagamentoController {

    private static final Logger logger = Logger.getLogger(PagamentoController.class.getName());
    private final  PagamentoService pagamentoService;

    //@Value("${api.key}")
    //private final String apiCartaoUrl;

    @PostMapping
    public ResponseEntity<String> registrarPagamento(@RequestBody PagamentoDTO dto) {

        //validaCartao(dto.getCpf());

        if(dto.getCpf() == null){
            throw new BadRequestResponse("O cpf do cliente não pode ser nulo ou não conter na base de dados");
        }
        if(dto.getNumero() == null || !Pattern.matches("\\d{4}(\\s?\\d{4}){3}", dto.getNumero())){
            throw new BadRequestResponse("O numero do cartão não pode ser nulo e deve conter apenas números com espaço");
        }
        if(dto.getDataValidade() == null){
            throw new BadRequestResponse("A data de validade do cartão não pode ser nulo");
        }
        if(dto.getCvv() == null || !Pattern.matches("\\d+", dto.getCvv())){
            throw new BadRequestResponse("O cvv do cartão não pode ser nulo e deve conter apenas números");
        }
        if(dto.getValor() == null || dto.getValor() <= 0){
            throw new BadRequestResponse("O valor da compra está nulo ou incorreto");
        }

        Pagamento pagamento = pagamentoService.registrarPagamento(dto);
        logger.info("Pagamento registrado com sucesso");
        return ResponseEntity.ok("chave_pagamento: "+ pagamento.getChavePagamento());
    }

    @GetMapping("/cliente/{chave}")
    public ResponseEntity<List<ConsultaPorChaveResponse>> consultarPagamentoCliente(@PathVariable String chave) {

        if(chave.replaceAll("\\D", "").isEmpty()){
            throw new BadRequestResponse("O cpf do cliente não pode ser vazio");
        }
        logger.info("Recebendo solicitação para consultar pagamento do cliente com a chave");
        List<Optional<Pagamento>> pagamentos = pagamentoService.consultarPagamentoCliente(chave.replaceAll("\\D", ""));

        if (pagamentos.isEmpty()) {
            logger.warning("Pagamento não encontrado para a chave");
            throw new NotFoundResponse("Pagamento não encontrado para a chave: " + chave);
        } else {
            logger.info("Pagamento encontrado:");
            return ResponseEntity.ok(ConsultaPorChaveResponse.fromPagamentos(pagamentos));
        }
    }

//    public void validaCartao(String cpf) {
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> retornoCPF = restTemplate.getForEntity(String.format("%s%s", apiCartaoUrl, cpf), String.class);
//        if (retornoCPF.getStatusCode().is4xxClientError()) {
//            throw new BadRequestResponse("Não é permitido operação de cartão com CPF inválido!");
//        }
//    }

}



