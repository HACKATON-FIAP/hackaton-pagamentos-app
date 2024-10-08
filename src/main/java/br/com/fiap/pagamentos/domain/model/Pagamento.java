package br.com.fiap.pagamentos.domain.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chavePagamento;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String dataValidade;

    @Column(nullable = false)
    private String cvv;

    @Column(nullable = false)
    private Integer valor;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String metodoPagamento;

    @Column(nullable = false)
    private String status;

}
