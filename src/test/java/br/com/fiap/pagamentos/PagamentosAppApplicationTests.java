package br.com.fiap.pagamentos;

import br.com.fiap.pagamentos.api.model.PagamentoDTO;
import br.com.fiap.pagamentos.domain.enums.TipoPagamento;
import br.com.fiap.pagamentos.domain.model.Pagamento;
import org.springframework.context.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PagamentosAppApplicationTests {
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
		assertThat(applicationContext).isNotNull();
	}

	@Test
	void allBeansShouldBeLoaded() {
		String[] allBeanNames = applicationContext.getBeanDefinitionNames();

		for (String beanName : allBeanNames) {
			Object bean = applicationContext.getBean(beanName);
			assertThat(bean).isNotNull();
		}
	}

	@Test
	void testPagamentoDTOInitialization() {
		PagamentoDTO dto = new PagamentoDTO();

		assertThat(dto.getIdCarrinho()).isNull();
		assertThat(dto.getValorTotal()).isNull();
		assertThat(dto.getTipoPagamento()).isNull();

		dto.setIdCarrinho(1L);
		dto.setTipoPagamento(TipoPagamento.BOLETO);
		dto.setValorTotal(100.00);

		assertThat(dto.getIdCarrinho()).isEqualTo(1L);
		assertThat(dto.getValorTotal()).isEqualTo(100.00);
		assertThat(dto.getTipoPagamento()).isEqualTo(TipoPagamento.BOLETO);

		PagamentoDTO dtoWithBuilder = PagamentoDTO.builder()
			.idCarrinho(1L)
			.valorTotal(100.00)
			.tipoPagamento(TipoPagamento.BOLETO)
			.build();

		assertThat(dtoWithBuilder.getIdCarrinho()).isEqualTo(1L);
		assertThat(dtoWithBuilder.getValorTotal()).isEqualTo(100.00);
		assertThat(dtoWithBuilder.getTipoPagamento()).isEqualTo(TipoPagamento.BOLETO);
	}

	@Test
	void testPagamentoInitialization() {
		Pagamento pagamento = new Pagamento();

		assertThat(pagamento.getIdPagamento()).isNull();
		assertThat(pagamento.getIdCarrinho()).isNull();
		assertThat(pagamento.getValorTotal()).isNull();
		assertThat(pagamento.getTipoPagamento()).isNull();

		pagamento.setIdPagamento(1L);
		pagamento.setIdCarrinho(1L);
		pagamento.setTipoPagamento(TipoPagamento.BOLETO);
		pagamento.setValorTotal(100.00);

		assertThat(pagamento.getIdPagamento()).isEqualTo(1L);
		assertThat(pagamento.getIdCarrinho()).isEqualTo(1L);
		assertThat(pagamento.getValorTotal()).isEqualTo(100.00);
		assertThat(pagamento.getTipoPagamento()).isEqualTo(TipoPagamento.BOLETO);

		Pagamento WithBuilder = Pagamento.builder()
				.idPagamento(1L)
				.idCarrinho(1L)
				.valorTotal(100.00)
				.tipoPagamento(TipoPagamento.BOLETO)
				.build();

		assertThat(WithBuilder.getIdPagamento()).isEqualTo(1L);
		assertThat(WithBuilder.getIdCarrinho()).isEqualTo(1L);
		assertThat(WithBuilder.getValorTotal()).isEqualTo(100.00);
		assertThat(WithBuilder.getTipoPagamento()).isEqualTo(TipoPagamento.BOLETO);
	}
}
