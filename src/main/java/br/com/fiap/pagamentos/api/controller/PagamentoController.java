package br.com.fiap.pagamentos.api.controller;

import br.com.fiap.pagamentos.api.model.CartaoDTO;
import br.com.fiap.pagamentos.api.model.PagamentoDTO;
import br.com.fiap.pagamentos.api.response.exception.BadRequestResponse;
import br.com.fiap.pagamentos.api.response.exception.NotFoundResponse;
import br.com.fiap.pagamentos.api.response.sucess.ConsultaPorChaveResponse;
import br.com.fiap.pagamentos.domain.model.Pagamento;
import br.com.fiap.pagamentos.domain.service.CartaoService;
import br.com.fiap.pagamentos.domain.service.PagamentoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private final CartaoService cartaoService;

    @Autowired
    public PagamentoController(CartaoService cartaoService, PagamentoService pagamentoService) {
        this.cartaoService = cartaoService;
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<String> registrarPagamento(@RequestBody PagamentoDTO dto) {

        CartaoDTO cartaoDTO = cartaoService.validarCartao(dto.getCpf());

        validarPagamento(dto);
        validarCartao(cartaoDTO, dto);

        cartaoDTO.setLimite(cartaoDTO.getLimite() - dto.getValor());
        cartaoService.update(cartaoDTO);
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

    public void validarPagamento (PagamentoDTO pagamento) {

        if(pagamento.getCpf() == null){
            throw new BadRequestResponse("O cpf do cliente não pode ser nulo ou não conter na base de dados");
        }
        if(pagamento.getNumero() == null || !Pattern.matches("\\d{4}(\\s?\\d{4}){3}", pagamento.getNumero())){
            throw new BadRequestResponse("O numero do cartão não pode ser nulo e deve conter apenas números com espaço");
        }
        if(pagamento.getDataValidade() == null){
            throw new BadRequestResponse("A data de validade do cartão não pode ser nulo");
        }
        if(pagamento.getCvv() == null || !Pattern.matches("\\d+", pagamento.getCvv())){
            throw new BadRequestResponse("O cvv do cartão não pode ser nulo e deve conter apenas números");
        }
        if(pagamento.getValor() == null || pagamento.getValor() <= 0){
            throw new BadRequestResponse("O valor da compra está nulo ou incorreto");
        }
    }

    public void validarCartao(CartaoDTO cartao, PagamentoDTO pagamento) {

        if(cartao == null){
            throw new BadRequestResponse("O cliente não está na nosa base de dados");
        }
        if(!cartao.getNumero().equals(pagamento.getNumero())){
            throw new BadRequestResponse("Esse cartão não está na nosa base de dados");
        }
        if(!cartao.getDataValidade().equals(pagamento.getDataValidade())){
            throw new BadRequestResponse("A data de validade está incorreta");
        }
        if(!cartao.getCvv().equals(pagamento.getCvv())){
            throw new BadRequestResponse("O cvv do cartão está incorreto");
        }
        if(cartao.getLimite() < pagamento.getValor()) {
            throw new BadRequestResponse("O Cartão não possui limite para essa operação.");
        }

    }
}



