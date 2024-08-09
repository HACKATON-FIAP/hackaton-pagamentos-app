package br.com.fiap.pagamentos.api.controller;

import br.com.fiap.pagamentos.api.model.CartaoDTO;
import br.com.fiap.pagamentos.api.model.CartaoDTODataFactory;
import br.com.fiap.pagamentos.api.model.PagamentoDTO;
import br.com.fiap.pagamentos.api.model.PagamentoDTODataFactory;
import br.com.fiap.pagamentos.api.response.exception.BadRequestResponse;
import br.com.fiap.pagamentos.api.response.exception.NotFoundResponse;
import br.com.fiap.pagamentos.api.response.sucess.ConsultaPorChaveResponse;
import br.com.fiap.pagamentos.domain.model.Pagamento;
import br.com.fiap.pagamentos.domain.model.PagamentoDataFactory;
import br.com.fiap.pagamentos.domain.repository.PagamentoRepository;
import br.com.fiap.pagamentos.domain.service.CartaoService;
import br.com.fiap.pagamentos.domain.service.PagamentoService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PagamentoControllerTest {
    private AutoCloseable closeable;

    @InjectMocks
    private PagamentoController pagamentoController;

    @Mock
    private PagamentoService pagamentoService;

    @Mock
    private CartaoService cartaoService;

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Nested
    class ControllerToServiceSave {
        @Nested
        class sucess {

            @Test
            void deveSalvarPagamentoControllerSettersAndGetters() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                Pagamento pagamento = PagamentoDataFactory.criarPagamentoSettersAndGetters();
                CartaoDTO cartaoDTO = CartaoDTODataFactory.criarCartaoDTOSettersAndGetters();

                // Mock
                when(cartaoService.validarCartao(pagamentoDTO.getCpf())).thenReturn(cartaoDTO);
                when(pagamentoService.registrarPagamento(pagamentoDTO)).thenReturn(pagamento);

                // Act
                ResponseEntity<String> response = pagamentoController.registrarPagamento(pagamentoDTO);
                // Assert
                Assertions.assertNotNull(response);
                Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
                Assertions.assertEquals("chave_pagamento: "+ pagamento.getChavePagamento(), response.getBody());
                verify(pagamentoService, times(1)).registrarPagamento(pagamentoDTO);
            }
        }
        @Nested
        class exceptionPagamento {
            @Test
            void deveRetornarExceptionQuandoCpfForNull() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                pagamentoDTO.setCpf(null);

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> {
                    pagamentoController.registrarPagamento(pagamentoDTO);
                });
            }

            @Test
            void deveRetornarExceptionQuandoNumeroForNull() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                pagamentoDTO.setNumero(null);

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> {
                    pagamentoController.registrarPagamento(pagamentoDTO);
                });
            }

            @Test
            void deveRetornarExceptionQuandoNumeroTiverCaracteres() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                pagamentoDTO.setNumero("A");

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> {
                    pagamentoController.registrarPagamento(pagamentoDTO);
                });
            }

            @Test
            void deveRetornarExceptionQuandoDataValidadeForNull() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                pagamentoDTO.setDataValidade(null);

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> {
                    pagamentoController.registrarPagamento(pagamentoDTO);
                });
            }

            @Test
            void deveRetornarExceptionQuandoCvvForNull() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                pagamentoDTO.setCvv(null);

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> {
                    pagamentoController.registrarPagamento(pagamentoDTO);
                });
            }

            @Test
            void deveRetornarExceptionQuandoCvvTiverCaracteres() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                pagamentoDTO.setCvv("A");

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> {
                    pagamentoController.registrarPagamento(pagamentoDTO);
                });
            }

            @Test
            void deveRetornarExceptionQuandoValorForNull() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                pagamentoDTO.setValor(null);

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> {
                    pagamentoController.registrarPagamento(pagamentoDTO);
                });
            }

            @Test
            void deveRetornarExceptionQuandoValorForNegativo() {
                // Arrange
                PagamentoDTO pagamentoDTO = new PagamentoDTO();
                pagamentoDTO.setValor(-10); // Valor inválido para gerar erro de validação

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> {
                    pagamentoController.registrarPagamento(pagamentoDTO);
                });
            }
        }
        @Nested
        class exceptionCartao{
            @Test
            void deveRetornarExceptionQuandoCpfForDiferenteDeCartao() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> { pagamentoController.registrarPagamento(pagamentoDTO); });
            }
            @Test
            void deveRetornarExceptionQuandoNumeroForDiferenteDeCartao() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                CartaoDTO cartaoDTO = CartaoDTODataFactory.criarCartaoDTOSettersAndGetters();
                cartaoDTO.setNumero("1");

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> { pagamentoController.validarCartao(cartaoDTO, pagamentoDTO);});
            }
            @Test
            void deveRetornarExceptionQuandoDataValidadeForDiferenteDeCartao() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                CartaoDTO cartaoDTO = CartaoDTODataFactory.criarCartaoDTOSettersAndGetters();
                cartaoDTO.setDataValidade("1");

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> { pagamentoController.validarCartao(cartaoDTO, pagamentoDTO);});
            }
            @Test
            void deveRetornarExceptionQuandoCvvForDiferenteDeCartao() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                CartaoDTO cartaoDTO = CartaoDTODataFactory.criarCartaoDTOSettersAndGetters();
                cartaoDTO.setCvv("1");

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> { pagamentoController.validarCartao(cartaoDTO, pagamentoDTO);});
            }
            @Test
            void deveRetornarExceptionQuandoValorMaiorQueCartao() {
                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                CartaoDTO cartaoDTO = CartaoDTODataFactory.criarCartaoDTOSettersAndGetters();
                cartaoDTO.setLimite(1L);

                // Act & Assert
                assertThrows(BadRequestResponse.class, () -> { pagamentoController.validarCartao(cartaoDTO, pagamentoDTO);});
            }

        }
    }
    @Nested
    class ControllerToServiceConsultar {

        @Nested
        class sucess {
            @Test
            void deveConsultarPagamentoControllerSettersAndGetters() {

                // Arrange
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                Pagamento pagamento = PagamentoDataFactory.criarPagamentoSettersAndGetters();
                List<Optional<Pagamento>> pagamentos = List.of(Optional.of(pagamento));

                // Mock
                when(pagamentoService.consultarPagamentoCliente(pagamentoDTO.getCpf())).thenReturn(pagamentos);

                // Act & Assert
                ResponseEntity<List<ConsultaPorChaveResponse>> responseConsulta = pagamentoController.consultarPagamentoCliente(pagamentoDTO.getCpf());

                Assertions.assertNotNull(responseConsulta);
                Assertions.assertEquals(HttpStatus.OK, responseConsulta.getStatusCode());
                Assertions.assertEquals(ConsultaPorChaveResponse.fromPagamentos(pagamentos), responseConsulta.getBody());
                verify(pagamentoService, times(1)).consultarPagamentoCliente(pagamentoDTO.getCpf());
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
            void deveRetornarExceptionPagamentoNaoForEncontrado(){
                // Arrange
                String chave = "111.111.111.11";// Valor inválido para gerar erro de validação

                // Act & Assert
                assertThrows(NotFoundResponse.class, () -> { pagamentoController.consultarPagamentoCliente(chave);});
            }

        }
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

}
