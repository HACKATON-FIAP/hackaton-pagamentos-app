package br.com.fiap.pagamentos.domain.repository;

import br.com.fiap.pagamentos.domain.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Optional<Pagamento>> findByCpf(String cpf);
    Pagamento save(Pagamento pagamento);

}
