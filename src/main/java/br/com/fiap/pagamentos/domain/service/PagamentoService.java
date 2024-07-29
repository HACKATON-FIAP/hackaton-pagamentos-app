package br.com.fiap.pagamentos.domain.service;

import br.com.fiap.pagamentos.api.model.PagamentoDTO;
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
    public void realizarPagamento (PagamentoDTO pagamentoDTO) {
        logger.info("Realizar Pagamento Service");
        var pagamento = modelMapper.map(pagamentoDTO, Pagamento.class);
        pagamentoRepository.save(pagamento);
    }
    public Optional<List<Pagamento>> buscarTodosPagamentos() {
        logger.info("Buscar Todos Pagamentos Service");
        return Optional.of(pagamentoRepository.findAll());
    }
    public Optional<Pagamento> buscarPagamentoPorId(Long id) {
        logger.info("Buscar Pagamento Por Id: ");
        return pagamentoRepository.findById(id);
    }

}
