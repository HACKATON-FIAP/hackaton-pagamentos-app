package br.com.fiap.pagamentos.domain.service;

import br.com.fiap.pagamentos.api.model.PagamentoDTO;
import br.com.fiap.pagamentos.api.model.PagamentoDTODataFactory;
import br.com.fiap.pagamentos.domain.exception.ServiceUnavailableResponse;
import br.com.fiap.pagamentos.domain.model.Pagamento;
import br.com.fiap.pagamentos.domain.model.PagamentoDataFactory;
import br.com.fiap.pagamentos.domain.repository.PagamentoRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PagamentoServiceTest {
    private AutoCloseable closeable;
    @InjectMocks
    private PagamentoService pagamentoService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private PagamentoRepository pagamentoRepository;
    @Mock
    private DataSource dataSource;
    @Mock
    private Connection connection;
    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }
    @Nested
    class ServiceToRepositorySave {
        @Nested
        class sucess {

            @Test
            void deveSalvarPagamentoServiceSettersAndGetters() throws Exception {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                Pagamento pagamento = PagamentoDataFactory.criarPagamentoSettersAndGetters();
                // Mock
                when(dataSource.getConnection()).thenReturn(connection);
                when(connection.isValid(2)).thenReturn(true);
                when(modelMapper.map(pagamentoDTO, Pagamento.class)).thenReturn(pagamento);
                // Act
                Pagamento p = pagamentoService.registrarPagamento(pagamentoDTO);

                // Assert
                assertNotNull(p);
                Assertions.assertEquals(pagamento, p);
                verify(modelMapper, times(1)).map(pagamentoDTO, Pagamento.class);
                verify(pagamentoRepository, times(1)).save(pagamento);
            }
        }
        @Nested
        class exception {
            @Test
            void deveLancarServiceUnavailableResponseQuandoConexaoComBancoFalha() throws Exception {
                // Arrange
                when(dataSource.getConnection()).thenThrow(new SQLException("Erro ao registrar pagamento nossos sistemas estão temporariamente indisponíveis no momento. Por favor, tente novamente mais tarde."));
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();

                // Act & Assert
                assertThrows(ServiceUnavailableResponse.class, () -> { pagamentoService.registrarPagamento(pagamentoDTO);});
            }
            @Test
            void deveLancarServiceUnavailableResponseQuandoConexaoNaoEValida() throws Exception {
                // Arrange
                when(dataSource.getConnection()).thenThrow(new ServiceUnavailableResponse("Erro ao registrar pagamento nossos sistemas estão temporariamente indisponíveis no momento. Por favor, tente novamente mais tarde."));
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();

                // Act & Assert
                assertThrows(ServiceUnavailableResponse.class, () -> {  pagamentoService.registrarPagamento(pagamentoDTO);});
            }

        }
    }
    @Nested
    class ServiceToRepositoryConsultar {

        @Nested
        class sucess {
            @Test
            void deveConsultarPagamentoServiceSettersAndGetters() throws Exception {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                Pagamento pagamento = PagamentoDataFactory.criarPagamentoSettersAndGetters();
                List<Optional<Pagamento>> pagamentos = List.of(Optional.of(pagamento));

                // Mock
                when(dataSource.getConnection()).thenReturn(connection);
                when(connection.isValid(2)).thenReturn(true);
                when(pagamentoRepository.findByCpf(pagamentoDTO.getCpf())).thenReturn(pagamentos);

                // Act & Assert
                List<Optional<Pagamento>> responseConsultar = pagamentoService.consultarPagamentoCliente(pagamentoDTO.getCpf());

                assertNotNull(responseConsultar);
                assertEquals(pagamentos, responseConsultar);
                verify(pagamentoRepository, times(1)).findByCpf(pagamentoDTO.getCpf());

            }
        }
        @Nested
        class exception{
            @Test
            void deveLancarServiceUnavailableResponseQuandoConexaoComBancoFalha() throws Exception {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                when(dataSource.getConnection()).thenThrow(new SQLException("Erro ao registrar pagamento nossos sistemas estão temporariamente indisponíveis no momento. Por favor, tente novamente mais tarde."));
                // Act & Assert
                assertThrows(ServiceUnavailableResponse.class, () -> {
                    pagamentoService.consultarPagamentoCliente(pagamentoDTO.getCpf());
                });
            }
            @Test
            void deveLancarServiceUnavailableResponseQuandoConexaoNaoEValida() throws Exception {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                when(dataSource.getConnection()).thenThrow(new ServiceUnavailableResponse("Erro ao registrar pagamento nossos sistemas estão temporariamente indisponíveis no momento. Por favor, tente novamente mais tarde."));

                // Act & Assert
                assertThrows(ServiceUnavailableResponse.class, () -> {
                    pagamentoService.consultarPagamentoCliente(pagamentoDTO.getCpf());
                });
            }
        }
    }
}