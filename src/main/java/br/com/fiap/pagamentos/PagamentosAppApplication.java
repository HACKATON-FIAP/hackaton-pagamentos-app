package br.com.fiap.pagamentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@EntityScan("br.com.fiap.pagamentos")
@EnableJpaRepositories(basePackages = "br.com.fiap.pagamentos")
public class PagamentosAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(PagamentosAppApplication.class, args);
	}
}
