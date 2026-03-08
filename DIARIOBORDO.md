# Diário de Bordo Bootcamp Java Deloitte

Projeto desenvolvido para estudo de lógica e fundamentos de Java


### Aula_1 e Aula_2
03-03-2026
  #### LEGADO
  * CRUD de Usuário (Cadastro, Consulta, Edição, Exclusão), com uso de:

    * Estrutura de Repetição `While`: para criação de um loop controlado, que permite que o usuário realize várias operações sem que o programa feche.
    * Seleção de Fluxo `switch-case`: escolhido ao invés do if-else por ser visualmente mais limpo e mais eficiente para criação de um menu, direcionando o fluxo.
    * Armazenamento `ArrayList`: Escolhido por ser dinâmico e oferecer métodos prontos de `.add()`, `.remove()`, `.set()`
    * Validação de segurança com `if-else`: para validar os índices, impedindo o acesso fora da posição que não existe.



### Aula_3
04-03-2026 e 05-03-2026
  * Mudança para sistema de To-Do List (Gestão de Tarefas):
    Decidi migrar o projeto de um cadastro de usuários para um Sistema de Gestão de Tarefas (estilo To-Do). O sistema evoluiu para uma estrutura organizada utilizando os pilares da Programação Orientada a Objetos (POO).
  * Classe | Objeto | Atributo:
    * Classe: Modelo para criação de objetos com atributos e métodos
    * Objetos: Instância que utiliza a estrutura da Classe com dados específicos, criado por meio do construtor. 
    * ATRIBUTO: Características em comum entre usuários da classe.
      * Uma classe que não é instanciada só possui variáveis, a partir do momento que é instanciada essas variáveis se tornam atributos. Atributos só são atributos se existir um objeto.

  * Encapsulamento de Métodos, agora as responsabilidades foram delegadas para a classe `Tarefa`, onde antes toda a lógica residia no médoto `main`.
    * Métodos static (Responsabilidade de Classe): Pertencem à Classe, eles olham para o "todo", para a lista completa de tarefas.
      * Listar/Buscar: Atuam sobre a static List, percorrendo a lista de tarefas para localizar registros.
      * Excluir: Manipula a coleção global para remover um item através da comparação de IDs.
    * Método de Instância (Responsabilidade do Objeto): Pertence ao `Objeto`, a tarefa já existe na memória.
      * Criar: Não é estático, pois utiliza os dados específicos daquela instância (this.titulo, etc.) para se adicionar à lista de tarefas.
  
  * Melhorias a serem feitas:
    * Enum para Status: Substituição da String genérica por um tipo enumerado (PENDENTE, EM_ANDAMENTO, CONCLUIDA), eliminando erros de digitação e padroniza o estado da tarefa.
    * Enum para Categoria: Organização das tarefas em grupos fixos (ESTUDOS, TRABALHO, PESSOAL, LAZER).
    * Evolução da arquitetura para o framework Spring Boot e Spring Data, com integração para armazenamento em Banco de Dados.

### Melhorias
08-03-2026 | Refatoração
  * Substituição de `String` pelos `Enum` Status e Categoria
    * Padronização dos dados e eliminação de erros de digitação
    * Uso de `.values()`: para exibição dinâmica das opções disponíveis ao usuário
  * Tratamento de Exceções `Try-Catch`:
    * Para capturar `IllegalArgumentException` durante a converão de entrada do usuário `valueOf`, impedindo que a aplicação encerre inesperadamente caso o usuário digite uma opção inexistente nos Enums.

