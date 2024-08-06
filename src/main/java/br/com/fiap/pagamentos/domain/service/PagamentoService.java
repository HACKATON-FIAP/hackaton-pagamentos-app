package br.com.fiap.pagamentos.domain.service;

import br.com.fiap.pagamentos.api.model.PagamentoDTO;
import br.com.fiap.pagamentos.api.response.sucess.ConsultaPorChaveResponse;
import br.com.fiap.pagamentos.domain.exception.InternalServerErrorResponse;
import br.com.fiap.pagamentos.domain.exception.ServiceUnavailableResponse;
import br.com.fiap.pagamentos.domain.model.Pagamento;
import br.com.fiap.pagamentos.domain.repository.PagamentoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Connection;
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

    public Pagamento registrarPagamento (PagamentoDTO pagamentoDTO) throws InternalServerErrorResponse {
        logger.info("Registrando pagamento");
        try (Connection connection = dataSource.getConnection()) {
            if (connection == null || !connection.isValid(2)) {
                logger.severe("Erro ao registrar pagamento");
                throw new ServiceUnavailableResponse("Nossos sistemas estão temporariamente indisponíveis no momento. Por favor, tente novamente mais tarde.");
            }
            Pagamento pagamento = modelMapper.map(pagamentoDTO, Pagamento.class);
            pagamentoRepository.save(pagamento);
            logger.info("Pagamento registrado com sucesso");
            return pagamento;
        } catch (ServiceUnavailableResponse e) {
            logger.severe("Erro ao registrar pagamento");
            throw e;
        } catch (Exception e) {
            logger.severe("Erro ao registrar pagamento");
            throw new InternalServerErrorResponse("Erro interno ao registrar pagamento");
        }
    }
    public List<Optional<Pagamento>> consultarPagamentoCliente(String chave) {

        try (Connection connection = dataSource.getConnection()) {
            if (connection == null || !connection.isValid(2)) {
                logger.severe("Erro ao buscar pagamentos");
                throw new ServiceUnavailableResponse("Nossos sistemas estão temporariamente indisponíveis no momento. Por favor, tente novamente mais tarde.");
            }
            List<Optional<Pagamento>> pagamentos = pagamentoRepository.findByCpf(chave);
            logger.info("Pagamentos encontrados");
            return pagamentos;
        } catch (Exception e) {
            logger.severe("Erro ao buscar pagamentos");
            throw new ServiceUnavailableResponse("Nossos sistemas estão temporariamente indisponíveis no momento. Por favor, tente novamente mais tarde.");
        }
    }
}
