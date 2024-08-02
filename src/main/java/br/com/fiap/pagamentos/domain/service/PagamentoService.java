package br.com.fiap.pagamentos.domain.service;

import br.com.fiap.pagamentos.api.model.PagamentoDTO;
import br.com.fiap.pagamentos.domain.exception.InternalServerErrorResponse;
import br.com.fiap.pagamentos.domain.model.Pagamento;
import br.com.fiap.pagamentos.domain.repository.PagamentoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
@Slf4j
public class PagamentoService {

    private static final Logger logger = Logger.getLogger(PagamentoService.class.getName());

    private final ModelMapper modelMapper;

    private PagamentoRepository pagamentoRepository;
    public Pagamento registrarPagamento (PagamentoDTO pagamentoDTO) throws InternalServerErrorResponse {
        logger.info("Registrando pagamento: " + pagamentoDTO);
        try {
            var pagamento = modelMapper.map(pagamentoDTO, Pagamento.class);
            pagamentoRepository.save(pagamento);
            logger.info("Pagamento registrado com sucesso: " + pagamento);
            return pagamento;
        } catch (Exception e) {
            logger.severe("Erro ao registrar pagamento: " + e.getMessage());
            throw new InternalServerErrorResponse("Erro interno ao registrar pagamento");
        }
    }
    public Optional<Pagamento> consultarPagamentoCliente(Long chave) {
        logger.info("Buscando pagamento por chave: " + chave);

        var pagamento = pagamentoRepository.findById(chave);

        logger.info("Pagamentos encontrados: " + pagamento);

        return pagamento;
    }

}
