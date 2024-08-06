package br.com.fiap.pagamentos.domain.repository;

import br.com.fiap.pagamentos.domain.model.Pagamento;
import br.com.fiap.pagamentos.domain.model.PagamentoDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PagamentoRepositoryIntegrationTest {

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
    class repositorySalvarEntity {
        @Test
        void deveSalvarPagamentoBuilder() {
            Pagamento pagamento = PagamentoDataFactory.criarPagamentoBuilder();
            Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);
            assertThat(pagamentoSalvo).isNotNull();
            assertThat(pagamentoSalvo.getChavePagamento()).isNotNull();
            assertThat(pagamentoSalvo.getCpf()).isEqualTo("12345678901");
        }

        @Test
        void deveSalvarPagamentoSettersAndGetters() {
            Pagamento pagamento = PagamentoDataFactory.criarPagamentoSettersAndGetters();
            Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);
            assertThat(pagamentoSalvo).isNotNull();
            assertThat(pagamentoSalvo.getChavePagamento()).isNotNull();
            assertThat(pagamentoSalvo.getCpf()).isEqualTo("12345678901");
        }
    }
    @Nested
    class repositoryConsultaPorChave {
      @Test
        void deveConsultarPagamentoBuilder() {
            Pagamento pagamento = PagamentoDataFactory.criarPagamentoBuilder();
            pagamentoRepository.save(pagamento);
            List<Optional<Pagamento>> p  = pagamentoRepository.findByCpf(pagamento.getCpf());
            assertThat(p).isNotNull();
            assertThat(p.getFirst()).isNotNull();
        }

        @Test
        void deveConsultarPagamentoSettersAndGetters() {
            Pagamento pagamento = PagamentoDataFactory.criarPagamentoSettersAndGetters();
            pagamentoRepository.save(pagamento);
            List<Optional<Pagamento>> pagamentoEncontrado = pagamentoRepository.findByCpf(pagamento.getCpf());
            assertThat(pagamentoEncontrado).isNotNull();
            assertThat(pagamentoEncontrado.getFirst()).isNotNull();
        }
    }
}
