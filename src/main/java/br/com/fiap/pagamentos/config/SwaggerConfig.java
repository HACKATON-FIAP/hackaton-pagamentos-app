package br.com.fiap.pagamentos.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Microsserviço de Pagamentos")
                .description("API desenvolvida para controlar todo o gerenciamento de pagamentos.")
                .version("1.0.0")
                .termsOfService("Termo de uso: Open Source")
                .license(new License()
                    .name("Apache 2.0")
                    .url("http://www.fiap.com.br")
                )
            ).externalDocs(
                new ExternalDocumentation()
                    .description("Ricardo | Douglas | Jefferson")
                    .url("http://www.fiap.com.br"));
    }
}
