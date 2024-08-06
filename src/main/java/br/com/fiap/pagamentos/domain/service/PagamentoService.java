package br.com.fiap.pagamentos.domain.service;

import br.com.fiap.pagamentos.api.model.PagamentoDTO;
import br.com.fiap.pagamentos.domain.exception.ServiceUnavailableResponse;
import br.com.fiap.pagamentos.domain.model.Pagamento;
import br.com.fiap.pagamentos.domain.repository.PagamentoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
@Slf4j
public class PagamentoService {

    private static final Logger logger = Logger.getLogger(PagamentoService.class.getName());
    private final ModelMapper modelMapper;
    private final PagamentoRepository pagamentoRepository;
    private final DataSource dataSource;

    public Pagamento registrarPagamento (PagamentoDTO pagamentoDTO)  {
        try (Connection ignored = dataSource.getConnection()) {
            Pagamento pagamento = modelMapper.map(pagamentoDTO, Pagamento.class);
            pagamentoRepository.save(pagamento);
            logger.info("Pagamento registrado com sucesso");
            return pagamento;
        } catch (ServiceUnavailableResponse | SQLException e) {
            throw new ServiceUnavailableResponse("Erro ao registrar pagamento nossos sistemas estão temporariamente indisponíveis no momento. Por favor, tente novamente mais tarde.");
        }

    }
    public List<Optional<Pagamento>> consultarPagamentoCliente(String chave) {
        try (Connection ignored = dataSource.getConnection()) {
            List<Optional<Pagamento>> pagamentos = pagamentoRepository.findByCpf(chave);
            logger.info("Pagamentos encontrados");
            return pagamentos;
        } catch (ServiceUnavailableResponse | SQLException e) {
            throw new ServiceUnavailableResponse("Erro ao registrar pagamento nossos sistemas estão temporariamente indisponíveis no momento. Por favor, tente novamente mais tarde.");
        }
    }
}
