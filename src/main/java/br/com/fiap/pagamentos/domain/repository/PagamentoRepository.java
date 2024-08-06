package br.com.fiap.pagamentos.domain.repository;

import br.com.fiap.pagamentos.api.response.sucess.ConsultaPorChaveResponse;
import br.com.fiap.pagamentos.domain.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    ConsultaPorChaveResponse findByCpf(String chave);
    Pagamento save(Pagamento pagamento);

}
