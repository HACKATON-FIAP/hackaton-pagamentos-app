package br.com.fiap.pagamentos.domain.service;

import br.com.fiap.pagamentos.api.model.PagamentoDTO;
import br.com.fiap.pagamentos.api.model.PagamentoDTODataFactory;
import br.com.fiap.pagamentos.api.response.sucess.ConsultaPorChaveDataFactory;
import br.com.fiap.pagamentos.api.response.sucess.ConsultaPorChaveResponse;
import br.com.fiap.pagamentos.domain.exception.InternalServerErrorResponse;
import br.com.fiap.pagamentos.domain.exception.ServiceUnavailableResponse;
import br.com.fiap.pagamentos.domain.model.Pagamento;
import br.com.fiap.pagamentos.domain.model.PagamentoDataFactory;
import br.com.fiap.pagamentos.domain.repository.PagamentoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
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
                assertEquals(pagamento, p);
                verify(modelMapper, times(1)).map(pagamentoDTO, Pagamento.class);
                verify(pagamentoRepository, times(1)).save(pagamento);
            }
        }
        @Nested
        class exception {
            @Test
            public void deveLancarServiceUnavailableResponseQuandoConexaoComBancoFalha() throws SQLException {
                // Arrange
                when(dataSource.getConnection()).thenReturn(null);
                PagamentoDTO pagamentoDTO = new PagamentoDTO();

                // Act & Assert
                assertThrows(ServiceUnavailableResponse.class, () -> { pagamentoService.registrarPagamento(pagamentoDTO);});
            }
            @Test
            public void deveLancarServiceUnavailableResponseQuandoConexaoNaoEValida() throws SQLException {
                // Arrange
                when(connection.isValid(2)).thenReturn(false);
                when(dataSource.getConnection()).thenReturn(connection);
                PagamentoDTO pagamentoDTO = new PagamentoDTO();

                // Act & Assert
                assertThrows(ServiceUnavailableResponse.class, () -> {  pagamentoService.registrarPagamento(pagamentoDTO);});
            }

            @Test
            public void deveLancarInternalServerErrorResponseQuandoOcorreExcecaoNaPersistencia() throws SQLException {
                // Arrange
                when(connection.isValid(2)).thenReturn(true);
                when(dataSource.getConnection()).thenReturn(connection);
                when(modelMapper.map(new PagamentoDTO(), Pagamento.class)).thenReturn(new Pagamento());
                when(pagamentoRepository.save(any())).thenThrow(new RuntimeException());

                // Act & Assert
                assertThrows(InternalServerErrorResponse.class, () -> { pagamentoService.registrarPagamento(new PagamentoDTO());});
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

                var response = ConsultaPorChaveDataFactory.criarConsultaPorChaveResponseSettersAndGetters();
                // Mock
                when(dataSource.getConnection()).thenReturn(connection);
                when(connection.isValid(2)).thenReturn(true);

                // Act
                pagamentoService.registrarPagamento(pagamentoDTO);
                List<Optional<Pagamento>> responseConsultar = pagamentoService.consultarPagamentoCliente(pagamentoDTO.getCpf());

                assertNotNull(responseConsultar);
                assertEquals(response, responseConsultar);
                verify(modelMapper, times(1)).map(pagamentoDTO, Pagamento.class);
                verify(pagamentoRepository, times(1)).findByCpf(pagamentoDTO.getCpf());

            }
        }
        @Nested
        class exception{
            @Test
            public void deveLancarServiceUnavailableResponseQuandoConexaoComBancoFalha() throws SQLException {
                // Arrange
                when(dataSource.getConnection()).thenReturn(null);
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();

                // Act & Assert
                assertThrows(ServiceUnavailableResponse.class, () -> { pagamentoService.consultarPagamentoCliente(pagamentoDTO.getCpf());});
            }
            @Test
            public void deveLancarServiceUnavailableResponseQuandoConexaoNaoEValida() throws SQLException {
                // Arrange
                when(connection.isValid(2)).thenReturn(false);
                when(dataSource.getConnection()).thenReturn(connection);
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();

                // Act & Assert
                assertThrows(ServiceUnavailableResponse.class, () -> {  pagamentoService.consultarPagamentoCliente(pagamentoDTO.getCpf());});
            }
        }
    }
}