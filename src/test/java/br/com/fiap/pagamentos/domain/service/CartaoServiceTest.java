package br.com.fiap.pagamentos.domain.service;

import br.com.fiap.pagamentos.api.model.CartaoDTO;
import br.com.fiap.pagamentos.domain.exception.ServiceUnavailableResponse;
import br.com.fiap.pagamentos.domain.repository.PagamentoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CartaoServiceTest {

    private AutoCloseable closeable;
    @InjectMocks
    private CartaoService cartaoService;
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

    @Test
    void deveLancarExceptionQuandoServicoCartaoIndisponivel() {
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.getForEntity(anyString(), eq(CartaoDTO.class))).thenThrow(new ServiceUnavailableResponse("Erro ao consultar cartão nossos sistemas estão temporariamente indisponíveis no momento. Por favor, tente novamente mais tarde."));

        cartaoService = new CartaoService(restTemplate);

        try {
            cartaoService.validarCartao("12345678909");
            fail("Deveria ter lançado uma ServiceUnavailableException");
        } catch (ServiceUnavailableResponse ex) {
            Assertions.assertEquals("Erro ao consultar cartão nossos sistemas estão temporariamente indisponíveis no momento. Por favor, tente novamente mais tarde.", ex.getMessage());
        }
    }
}
