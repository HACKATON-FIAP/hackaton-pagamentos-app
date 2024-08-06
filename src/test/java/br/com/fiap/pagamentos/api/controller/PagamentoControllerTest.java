package br.com.fiap.pagamentos.api.controller;

import br.com.fiap.pagamentos.api.model.PagamentoDTO;
import br.com.fiap.pagamentos.api.model.PagamentoDTODataFactory;
import br.com.fiap.pagamentos.api.response.exception.BadRequestResponse;
import br.com.fiap.pagamentos.api.response.exception.NotFoundResponse;
import br.com.fiap.pagamentos.api.response.sucess.ConsultaPorChaveDataFactory;
import br.com.fiap.pagamentos.api.response.sucess.ConsultaPorChaveResponse;
import br.com.fiap.pagamentos.domain.model.Pagamento;
import br.com.fiap.pagamentos.domain.model.PagamentoDataFactory;
import br.com.fiap.pagamentos.domain.repository.PagamentoRepository;
import br.com.fiap.pagamentos.domain.service.PagamentoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PagamentoControllerTest {
    private AutoCloseable closeable;
    @InjectMocks
    private PagamentoController pagamentoController;

    @Mock
    private PagamentoService pagamentoService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PagamentoRepository pagamentoRepository;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }
    @Nested
    class ControllerToServiceSave {
        @Nested
        class sucess {

            @Test
            void deveSalvarPagamentoControllerSettersAndGetters() throws Exception {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                Pagamento pagamento = PagamentoDataFactory.criarPagamentoSettersAndGetters();
                // Mock
                when(pagamentoService.registrarPagamento(pagamentoDTO)).thenReturn(pagamento);
                // Act
                ResponseEntity<Long> response = pagamentoController.registrarPagamento(pagamentoDTO);
                // Assert
                assertNotNull(response);
                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(pagamento.getChavePagamento(), response.getBody());
                verify(pagamentoService, times(1)).registrarPagamento(pagamentoDTO);
            }
        }
        @Nested
        class exception{
            @Test
            void deveRetornarExceptionQuandoCpfForNull() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                pagamentoDTO.setCpf(null);

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> { pagamentoController.registrarPagamento(pagamentoDTO);});
            }
            @Test
            void deveRetornarExceptionQuandoNumeroForNull() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                pagamentoDTO.setNumero(null);

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> { pagamentoController.registrarPagamento(pagamentoDTO);});
            }
            @Test
            void deveRetornarExceptionQuandoNumeroTiverCaracteres() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                pagamentoDTO.setNumero("A");

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> { pagamentoController.registrarPagamento(pagamentoDTO);});
            }
            @Test
            void deveRetornarExceptionQuandoDataValidadeForNull() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                pagamentoDTO.setDataValidade(null);

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> { pagamentoController.registrarPagamento(pagamentoDTO);});
            }
            @Test
            void deveRetornarExceptionQuandoCvvForNull() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                pagamentoDTO.setCvv(null);

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> { pagamentoController.registrarPagamento(pagamentoDTO);});
            }
            @Test
            void deveRetornarExceptionQuandoCvvTiverCaracteres() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                pagamentoDTO.setCvv("A");

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> { pagamentoController.registrarPagamento(pagamentoDTO);});
            }
            @Test
            void deveRetornarExceptionQuandoValorForNull() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                pagamentoDTO.setValor(null);

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> { pagamentoController.registrarPagamento(pagamentoDTO);});
            }
            @Test
            void deveRetornarExceptionQuandoValorForNegativo() {
                // Arrange
                PagamentoDTO pagamentoDTO = new PagamentoDTO();
                pagamentoDTO.setValor(-10); // Valor inválido para gerar erro de validação

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> { pagamentoController.registrarPagamento(pagamentoDTO);});
            }
        }
    }
    @Nested
    class ControllerToServiceConsultar {

        @Nested
        class sucess {
            @Test
            void deveConsultarPagamentoControllerSettersAndGetters() throws Exception {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                Pagamento pagamento = PagamentoDataFactory.criarPagamentoSettersAndGetters();
                var response = ConsultaPorChaveDataFactory.criarConsultaPorChaveResponseSettersAndGetters();
                // Mock
                when(pagamentoService.registrarPagamento(pagamentoDTO)).thenReturn(pagamento);
                when(pagamentoService.consultarPagamentoCliente(pagamentoDTO.getCpf())).thenReturn(response);
                // Act
                pagamentoController.registrarPagamento(pagamentoDTO);
                ResponseEntity<ConsultaPorChaveResponse> responseConsultar = pagamentoController.consultarPagamentoCliente(pagamentoDTO.getCpf());
                // Assert
                assertNotNull(responseConsultar);
                assertEquals(HttpStatus.OK, responseConsultar.getStatusCode());
                assertEquals(response, responseConsultar.getBody());
                verify(pagamentoService, times(1)).registrarPagamento(pagamentoDTO);

            }
        }
        @Nested
        class exception{
            @Test
            void deveRetornarExceptionQuandoChaveForNull() {
                // Arrange
                String chave = "";// Valor inválido para gerar erro de validação

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> { pagamentoController.consultarPagamentoCliente(chave);});
            }

            @Test
            void deveRetornarExceptionPagamentoNaoForEncontrado() throws Exception {
                // Arrange
                String chave = "111.111.111.11";// Valor inválido para gerar erro de validação

                // Act & Assert
                assertThrows(NotFoundResponse.class, () -> { pagamentoController.consultarPagamentoCliente(chave);});
            }

        }
    }
}
