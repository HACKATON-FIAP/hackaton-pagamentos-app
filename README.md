# Pagamentos API

## Estrutura do Projeto

A aplicação está organizada nos seguintes pacotes:

### `com.fiap.pagamentos`

#### `api`
- **controller**
  - `PagamentoController.java`: Controlador responsável por gerenciar as requisições relacionadas aos pagamentos.
- **handler**
  - `ApiExceptionHandler.java`: Classe responsável por tratar exceções lançadas pela aplicação e retornar respostas apropriadas.
- **model**
  - `PagamentoDTO.java`: Data Transfer Object (DTO) utilizado para transferir dados de pagamento entre as camadas da aplicação.
- **response**
  - **exception**
    - `BadRequestResponse.java`: Resposta para erros de requisição inválida (400).
    - `NotFoundResponse.java`: Resposta para recursos não encontrados (404).
    - `UnauthorizedResponse.java`: Resposta para erros de autenticação (401).
  - **success**
    - `ConsultaPorChaveResponse.java`: Resposta de sucesso para consultas de pagamento por chave.

#### `config`
- **ModelMapperConfig.java**: Configuração do ModelMapper para mapeamento de objetos.
- **SwaggerConfig.java**: Configuração do Swagger para documentação da API.

#### `domain`
- **exception**
  - `InternalServerErrorException.java`: Exceção para erros internos do servidor (500).
  - `ServiceUnavailableException.java`: Exceção para serviços indisponíveis (503).
- **model**
  - `Pagamento.java`: Entidade que representa um pagamento no domínio da aplicação.
- **repository**
  - `PagamentoRepository.java`: Interface de repositório para operações de persistência de pagamentos.
- **service**
  - `PagamentoService.java`: Serviço que contém a lógica de negócios relacionada aos pagamentos.

## Funcionamento da Aplicação

### Controladores
Os controladores são responsáveis por receber as requisições HTTP, processá-las e retornar as respostas apropriadas. O `PagamentoController` gerencia todas as operações relacionadas aos pagamentos.

### Tratamento de Exceções
O `ApiExceptionHandler` captura exceções lançadas pela aplicação e retorna respostas padronizadas com os códigos de status HTTP apropriados.

### Modelos e DTOs
Os modelos representam as entidades do domínio da aplicação, enquanto os DTOs são utilizados para transferir dados entre as camadas da aplicação.

### Respostas
As respostas são organizadas em pacotes para diferenciar entre respostas de sucesso e respostas de erro, facilitando a manutenção e a clareza do código.

### Configurações
As configurações da aplicação, como o mapeamento de objetos e a documentação da API, são centralizadas no pacote `config`.

### Repositórios e Serviços
Os repositórios são responsáveis pelas operações de persistência, enquanto os serviços contêm a lógica de negócios da aplicação.

