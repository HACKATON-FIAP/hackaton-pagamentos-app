package br.com.fiap.pagamentos.controller;

import br.com.fiap.pagamentos.api.controller.PagamentoController;
import br.com.fiap.pagamentos.api.model.PagamentoDTO;
import br.com.fiap.pagamentos.domain.enums.TipoPagamento;
import br.com.fiap.pagamentos.domain.model.Pagamento;
import br.com.fiap.pagamentos.domain.service.PagamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
