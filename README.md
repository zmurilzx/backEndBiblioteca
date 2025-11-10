# Back-end Biblioteca

Aplicação REST construída com Spring Boot para gestão de uma biblioteca, incluindo cadastro de livros, clientes, fornecedores, formas de pagamento e controle de empréstimos. O projeto expõe uma API protegida por autenticação **HTTP Basic** e utiliza MySQL como banco de dados relacional.

## Sumário

- [Recursos principais](#recursos-principais)
- [Arquitetura e tecnologias](#arquitetura-e-tecnologias)
- [Pré-requisitos](#pré-requisitos)
- [Configuração do ambiente](#configuração-do-ambiente)
- [Execução da aplicação](#execução-da-aplicação)
- [Autenticação](#autenticação)
- [Endpoints principais](#endpoints-principais)
- [Boas práticas e validações](#boas-práticas-e-validações)
- [Contribuindo](#contribuindo)

## Recursos principais

- **Livros**: CRUD completo, busca paginada por título e filtros para livros disponíveis/emprestados.
- **Clientes**: Cadastro com validações de CPF, RG, e-mail e telefone, busca por diversos critérios e listagem de inadimplentes (empréstimos em atraso).
- **Empréstimos**: Registro, devolução, listagem por status, cliente ou bibliotecário responsável, além de relatório de empréstimos atrasados.
- **Fornecedores e formas de pagamento**: Cadastro e manutenção de fornecedores, incluindo indicadores agregados, e formas de pagamento aceitas pela biblioteca.
- **Integração ViaCEP**: Consulta de endereço por CEP com cache em memória.

## Arquitetura e tecnologias

- **Java 17** e **Spring Boot 3.3**
- **Spring Web**, **Spring Data JPA** e **Hibernate Validator**
- **Spring Security** com autenticação Basic
- **MySQL** como banco de dados
- **MapStruct** para mapeamento entre entidades e DTOs
- **Maven** como gerenciador de dependências

A estrutura do código segue uma organização em camadas (`controllers`, `services`, `repositories`, `entities`, `dto`, `mappers`), facilitando a manutenção e evolução do projeto.

## Pré-requisitos

Antes de iniciar, garanta que você tem instalado:

- [JDK 17+](https://adoptium.net/)
- [Maven 3.9+](https://maven.apache.org/download.cgi)
- [MySQL 8+](https://dev.mysql.com/downloads/mysql/)

## Configuração do ambiente

1. Clone o repositório e acesse a pasta do projeto:
   ```bash
   git clone https://github.com/<seu-usuario>/backEndBiblioteca.git
   cd backEndBiblioteca
   ```
2. Crie um banco de dados chamado `backEndBiblioteca` (ou ajuste o nome conforme sua preferência):
   ```sql
   CREATE DATABASE backEndBiblioteca CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```
3. Atualize o arquivo [`src/main/resources/application.properties`](src/main/resources/application.properties) com as credenciais do seu banco MySQL.
4. Opcional: altere as credenciais padrão de segurança (veja [Autenticação](#autenticação)).

> ℹ️ O projeto utiliza `spring.jpa.hibernate.ddl-auto=update`, permitindo que o Hibernate mantenha o schema sincronizado automaticamente. Para ambientes de produção recomenda-se utilizar migrações versionadas (Flyway ou Liquibase).

## Execução da aplicação

Para executar em modo desenvolvimento utilize o Maven:

```bash
mvn spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

Também é possível gerar o artefato e executar o JAR:

```bash
mvn clean package
java -jar target/backEndBiblioteca-1.0-SNAPSHOT.jar
```

## Autenticação

Todas as rotas são protegidas por autenticação HTTP Basic. As credenciais padrão estão definidas em `application.properties`:

- Usuário: `bibliotecario`
- Senha: `biblioteca123`

> **Importante:** altere essas credenciais antes de publicar em produção. Você pode utilizar usuários gerenciados pelo Spring Security ou integrar com um provedor externo.

## Endpoints principais

| Recurso | Método | Caminho | Descrição |
|---------|--------|---------|-----------|
| Livros | `GET` | `/livros` | Lista livros com paginação (`pagina`, `tamanho`). |
| Livros | `GET` | `/livros/buscar?titulo=...` | Busca paginada por título. |
| Livros | `GET` | `/livros/disponiveis` | Lista livros com estoque > 0. |
| Livros | `POST` | `/livros` | Cria um novo livro. |
| Clientes | `GET` | `/clientes` | Lista clientes com paginação. |
| Clientes | `GET` | `/clientes/cpf/{cpf}` | Busca cliente pelo CPF. |
| Clientes | `GET` | `/clientes/endereco/{cep}` | Consulta endereço via ViaCEP. |
| Clientes | `POST` | `/clientes` | Cria um novo cliente. |
| Empréstimos | `POST` | `/emprestimos` | Registra um empréstimo. |
| Empréstimos | `PATCH` | `/emprestimos/{id}/devolucao` | Registra devolução e multa. |
| Empréstimos | `GET` | `/emprestimos?status=ATIVO` | Lista empréstimos filtrando por status. |
| Empréstimos | `GET` | `/emprestimos/cliente/{clienteId}` | Lista empréstimos de um cliente (opcional `apenasAtivos`). |
| Empréstimos | `GET` | `/emprestimos/atrasados` | Lista empréstimos com devolução atrasada. |
| Fornecedores | `GET` | `/fornecedores` | Lista fornecedores com paginação. |
| Fornecedores | `GET` | `/fornecedores/indicadores` | KPIs de fornecedores e estoque. |
| Formas de pagamento | `GET` | `/formas-pagamento` | Lista formas de pagamento disponíveis. |

> Consulte os controllers no pacote [`org.example.controllers`](src/main/java/org/example/controllers) para explorar todas as rotas e parâmetros disponíveis.

## Boas práticas e validações

- Validadores customizados para CPF, e-mail e telefone garantem a integridade dos dados dos clientes.
- Regras de negócio evitam duplicidades (ex.: ISBN, CPF, RG, e-mail) e verificam campos obrigatórios.
- As exceções de domínio retornam mensagens amigáveis para facilitar o consumo da API.
- Cache em memória para consultas de CEP reduz chamadas redundantes à API do ViaCEP.

## Contribuindo

1. Faça um fork do repositório e crie uma branch para sua feature ou correção.
2. Garanta que os testes existentes passam (`mvn test`).
3. Envie um pull request descrevendo as mudanças e os passos para testar.

Sinta-se à vontade para abrir issues com sugestões, bugs ou dúvidas!
