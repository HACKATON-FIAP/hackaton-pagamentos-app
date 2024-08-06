package br.com.fiap.pagamentos.api.controller;

import br.com.fiap.pagamentos.api.model.PagamentoDTO;
import br.com.fiap.pagamentos.api.model.PagamentoDTODataFactory;
import br.com.fiap.pagamentos.api.response.sucess.ConsultaPorChaveResponse;
import br.com.fiap.pagamentos.domain.model.Pagamento;
import br.com.fiap.pagamentos.domain.repository.PagamentoRepository;
import br.com.fiap.pagamentos.domain.service.PagamentoService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PagamentoControllerIntegrationTest {

    @Autowired
    private PagamentoService pagamentoController;
    @Autowired
    private PagamentoRepository pagamentoRepository;

    private final static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("db_pagamentos")
            .withUsername("postgres")
            .withPassword("teste123");

    static {
        postgresContainer.start();
        System.setProperty("spring.datasource.url", postgresContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgresContainer.getUsername());
        System.setProperty("spring.datasource.password", postgresContainer.getPassword());
    }

    @Nested
    class ControllerSave {
        @Nested
        class sucess{

            @Test
            void deveSalvarPagamentoSettersAndGetters() {
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                Pagamento pagamentoSalvo = pagamentoController.registrarPagamento(pagamentoDTO);
                assertThat(pagamentoSalvo).isNotNull();
                assertThat(pagamentoSalvo.getChavePagamento()).isNotNull();
                assertThat(pagamentoSalvo.getCpf()).isEqualTo("12345678901");
            }
        }
        @Nested
        class exception{

        }


    }
    @Nested
    class ControllerConsultaPorChave {

        @Nested
        class sucess {
            @Test
            void deveConsultarPagamentoSettersAndGetters() {
                PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
                pagamentoController.registrarPagamento(pagamentoDTO);
                ConsultaPorChaveResponse pagamentoEncontrado = pagamentoController.consultarPagamentoCliente(pagamentoDTO.getCpf());
                assertThat(pagamentoEncontrado).isNotNull();
                assertThat(pagamentoEncontrado.getValor()).isNotNull();
            }
        }
        @Nested
        class exception{

        }
    }
}


