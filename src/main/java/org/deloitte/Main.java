package org.deloitte;

import org.deloitte.Model.Tarefa;
import java.util.Scanner;

public class Main {
    static void main() {

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 5) {
            System.out.println("---ToDo--");
            System.out.println("1 - Criar Tarefa");
            System.out.println("2 - Listar Taferas");
            System.out.println("3 - Editar Tarefa");
            System.out.println("4 - Excluir Tarefa");
            System.out.println("5 - Sair");
            System.out.println("Escolha a opção desejada: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Título: ");
                    String titulo = scanner.nextLine();

                    System.out.println("Categoria: ");
                    String categoria = scanner.nextLine();

                    System.out.println("Status: ");
                    String status = scanner.nextLine();

                    Tarefa novaTarefa = new Tarefa(titulo, categoria, status);
                    novaTarefa.cadastrar();
                    break;

                case 2:
                    Tarefa.listar();
                    break;

                case 3:
                    System.out.print("ID para Tarefa: ");
                    long id = scanner.nextLong();
                    scanner.nextLine();

                    Tarefa tarefaEncontrada = Tarefa.buscar(id);

                    if(tarefaEncontrada != null){
                        System.out.println("O que deseja editar:");
                        System.out.println(" 1 - Titulo");
                        System.out.println(" 2 - Categoria");
                        System.out.println(" 3 - Status");
                        System.out.println(" 4 - Cancelar");
                        System.out.print("Escolha: ");

                        int op = scanner.nextInt();
                        scanner.nextLine();

                        if (op == 1) {
                            System.out.println("Novo Título: ");
                            tarefaEncontrada.titulo = scanner.nextLine();
                            System.out.println("Título atualizado");
                        } else if (op == 2) {
                            System.out.println("Nova Categoria: ");
                            tarefaEncontrada.categoria = scanner.nextLine();
                            System.out.println("Categoria atualizada");
                        } else if (op == 3) {
                            System.out.println("Novo Status: ");
                            tarefaEncontrada.status = scanner.nextLine();
                            System.out.println("Status atualizado");
                        }else if(op ==4){
                            System.out.println("Edição cancelada");
                        }else {
                            System.out.print("Opção Inválida ");
                            return;
                        }
                    }
                    break;
                case 4:
                    System.out.print("ID para excluir: ");
                    long idEx = scanner.nextLong();

                    Tarefa.excluir(idEx);
                    break;
                case 5:
                    System.out.print("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida");
                    break;
            }
        }
        scanner.close();

    }
}
