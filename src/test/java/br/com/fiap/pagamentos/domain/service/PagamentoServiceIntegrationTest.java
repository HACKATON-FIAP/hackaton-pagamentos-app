/*
package br.com.fiap.pagamentos.domain.service;

import br.com.fiap.pagamentos.api.model.PagamentoDTO;
import br.com.fiap.pagamentos.api.model.PagamentoDTODataFactory;
import br.com.fiap.pagamentos.api.response.sucess.ConsultaPorChaveResponse;
import br.com.fiap.pagamentos.domain.model.Pagamento;
import br.com.fiap.pagamentos.domain.repository.PagamentoRepository;
import org.junit.jupiter.api.BeforeEach;
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
class PagamentoServiceIntegrationTest {
    @Autowired
    private PagamentoService pagamentoService;
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

    @BeforeEach
    void setUp() {
        pagamentoRepository.deleteAll();
    }

    @Nested
    class ServicetoRepositorySave {

        @Test
        void deveSalvarPagamentoSettersAndGetters() {
            PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
            Pagamento pagamentoSalvo = pagamentoService.registrarPagamento(pagamentoDTO);
            assertThat(pagamentoSalvo).isNotNull();
            assertThat(pagamentoSalvo.getChavePagamento()).isNotNull();
            assertThat(pagamentoSalvo.getCpf()).isEqualTo("12345678901");
        }
    }
    @Nested
    class ServicetoRepositoryConsultaPorChave {
        @Test
        void deveConsultarPagamentoSettersAndGetters() {
            PagamentoDTO pagamentoDTO = PagamentoDTODataFactory.criarPagamentoDTOSettersAndGetters();
            pagamentoService.registrarPagamento(pagamentoDTO);
            ConsultaPorChaveResponse pagamentoEncontrado = pagamentoService.consultarPagamentoCliente(pagamentoDTO.getCpf());
            assertThat(pagamentoEncontrado).isNotNull();
            assertThat(pagamentoEncontrado.getValor()).isNotNull();
        }
    }
}
*/
