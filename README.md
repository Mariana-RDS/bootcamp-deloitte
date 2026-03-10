# TaskList - Deloitte Bootcamp

Este projeto consiste numa aplicação para gerenciamento de tarefas (To-Do List).

### Tecnologias
  * Linguagem: Java 25
  * Framework: Spring Boot 3.5.11
  * Persistência: Spring Data JPA
  * Banco de Dados: H2 Database
  * Mapeamento: MapStruct (Conversão DTO/Entity)
  * Produtividade: Lombok
  * Documentação: Swagger UI / OpenAPI 3

### Arquitetura
A aplicação segue o padrão de Arquitetura em Camadas, garantindo a separação de responsabilidades e facilitando a manutenção:
1. Controller
2. Service
3. Repository
4. Entity
5. DTO

### Funcionalidades
O sistema permite a gestão completa do ciclo de vida de uma tarefa:
* Create: Cadastro de tarefas com Título, Status e Categoria.
* Read: Listagem de registros.
* Update: Edição parcial de atributos, permitindo alterar apenas o campo desejado sem sobrescrever todo o objeto.
* Delete: Remoção de tarefas através do identificador único (ID).

