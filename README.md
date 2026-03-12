# TaskList - Deloitte Bootcamp

Este projeto consiste numa aplicação para gerenciamento de tarefas (To-Do List).

### Tecnologias
- **Linguagem:** Java 25
- **Framework:** Spring Boot 3.5.11
- **Persistência:** Spring Data JPA
- **Banco de Dados:** H2 Database
- **Mapeamento:** MapStruct (Conversão DTO/Entity)
- **Produtividade:** Lombok
- **Documentação:** Swagger UI / OpenAPI 3

### Arquitetura
A aplicação segue o padrão de Arquitetura em Camadas, garantindo a separação de responsabilidades e facilitando a manutenção:
1. Controller
2. Service
3. Repository
4. Entity
5. DTO

### Funcionalidades
O sistema permite a gestão completa do ciclo de vida de uma tarefa:
- **Create:** Cadastro de tarefas com Título, Status e Categoria.
- **Read:** Listagem de registros.
- **Update:** Edição parcial de atributos, permitindo alterar apenas o campo desejado sem sobrescrever todo o objeto.
- **Delete:** Remoção de tarefas através do identificador único (ID).

### Como rodar o Projeto
  * Pré-requisitos
    * Java JDK 25
    * Maven 3.9+
    * IntelliJ IDEA
  * Execução
    * Abra o Projeto no IntelliJ
    * Localize a classe `TaskMasterApplication` que contém a classe `main`
    * Clique com o botão direito na classe e selecione Run `TaskMasterApplication`
  * Testando a API com Swagger
    * Uma vez que a aplicação está rodando:
      * Abra o navegador e acesse o endereço (http://localhost:8080/swagger-ui/index.html)
      * Lá você poderá visualizar todos os métodos e testá-los.
  * Acessa o Banco de Dados (H2)
    * Para visualizar as tabelas e dados
      * URL: http://localhost:8080/h2-console/
      * JDBC URL, User e Password definidos em `application.yml`

### Garantia de Qualidade (Testes)
O projeto segue as melhores práticas de desenvolvimento orientado a testes.
1. Teste Unitários (Camada de Serviço)
- **Tecnologias:** JUnit 5, Mockito
- **Foco:** Validação de regras de negócio, tratamento de exceções
2. Testes de Integração (Camada de Repositório)
- **Tecnologias:** @DataJpaTest, H2 Database, AssertJ.
- **Foco:** Validação da camada de persistência e integridade das queries SQL geradas pelo Spring Data JPA.