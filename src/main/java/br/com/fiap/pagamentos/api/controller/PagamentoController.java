package br.com.fiap.pagamentos.api.controller;

import br.com.fiap.pagamentos.api.model.PagamentoDTO;
import br.com.fiap.pagamentos.domain.model.Pagamento;
import br.com.fiap.pagamentos.domain.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/pagamento")
@AllArgsConstructor
public class PagamentoController {

    private PagamentoService pagamentoService;
    private ModelMapper modelMapper;

    @PostMapping("/processar")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> realizarPagamento(@Valid @RequestBody PagamentoDTO dto) {
        pagamentoService.realizarPagamento(dto);
        return ResponseEntity.ok(buildResponse(dto, 200, "Pagamento Realizado com Sucesso!"));
    }

    @GetMapping("/pagamentos")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Map<String, Object>> buscarTodosPagamentos() {

        List<Pagamento> pagamentos = pagamentoService.buscarTodosPagamentos().orElse(Collections.emptyList());

        if (pagamentos.isEmpty()){
            return ResponseEntity.badRequest().body(buildResponse(pagamentos, 404, "Nenhum pagamento encontrado"));
        }else{
            return ResponseEntity.ok(buildResponse(pagamentos, 200, "Pagamentos recuperados com sucesso"));
        }
    }

    @GetMapping("/pagamentos/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Map<String, Object>> buscarPagamentoPorId(@PathVariable Long id) {

        Optional<Pagamento> pagamento = pagamentoService.buscarPagamentoPorId(id);

        if (pagamento.isEmpty()){
            return ResponseEntity.badRequest().body(buildResponse(pagamento, 404, "Nenhum pagamento encontrado"));
        }else{
            return ResponseEntity.ok(buildResponse(pagamento, 200, "Pagamento recuperado com sucesso"));
        }
    }

    private Map<String, Object> buildResponse(Object data, int code, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("Dados Do Pagamento: ", data);
        response.put("Código Status: ", code);
        response.put("Mensagem: ", message);
        return response;
    }
}



