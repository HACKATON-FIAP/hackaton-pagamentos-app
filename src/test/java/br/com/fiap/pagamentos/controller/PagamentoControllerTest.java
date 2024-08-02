package br.com.fiap.pagamentos.controller;

import br.com.fiap.pagamentos.api.controller.PagamentoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(PagamentoController.class)
@AutoConfigureMockMvc
public class PagamentoControllerTest {

    @MockBean
    private PagamentoController pagamentoController;

    @BeforeEach
    void setUp() {

    }
    @Test
    public void deveRealizarPagamentoComSucesso() {

    }
    @Test
    void deveRetornarNenhumPagamentoEncontrado() {

    }
    @Test
    void deveBuscarPagamentoPorId() {

    }
}
