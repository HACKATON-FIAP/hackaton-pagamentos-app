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

import java.util.logging.Logger;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/pagamentos")
@AllArgsConstructor
public class PagamentoController {

    private static final Logger logger = Logger.getLogger(PagamentoController.class.getName());
    private final  PagamentoService pagamentoService;

    @PostMapping()
    public ResponseEntity<Long> registrarPagamento(@RequestBody PagamentoDTO dto) {
        if(dto.getCpf() == null){
            throw new BadRequestResponse("O cpf do cliente não pode ser nulo");
        }
        if(dto.getNumero() == null || !Pattern.matches("\\d+", dto.getNumero())){
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
        return ResponseEntity.ok(pagamento.getChavePagamento());
    }

    @GetMapping("/cliente/{chave}")
    public ResponseEntity<ConsultaPorChaveResponse> consultarPagamentoCliente(@PathVariable String chave) {

        chave.replaceAll("\\D", "");

        if(chave.isEmpty()){
            throw new BadRequestResponse("O cpf do cliente não pode ser vazio");
        }
        logger.info("Recebendo solicitação para consultar pagamento do cliente com a chave");
        ConsultaPorChaveResponse pagamentos = pagamentoService.consultarPagamentoCliente(chave);

        if (pagamentos != null) {
            logger.info("Pagamento encontrado:");
            return ResponseEntity.ok(pagamentos);
        } else {
            logger.warning("Pagamento não encontrado para a chave");
            throw new NotFoundResponse("Pagamento não encontrado para a chave: " + chave);
        }
    }

}



