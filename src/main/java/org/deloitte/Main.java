package org.deloitte;

import org.deloitte.User.User;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static void main() {

        Scanner scanner = new Scanner(System.in);
        ArrayList<User> usuarios = new ArrayList<>();
        int opcao = -1;

        while (opcao != 0) {

            System.out.println("---Cadastro de Usuário--");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("3 - Editar");
            System.out.println("4 - Excluir");
            System.out.println("Escolha a opção desejada: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Insira o nome do usuário: ");
                    String nome = scanner.next();

                    System.out.println("Insira o email do usuário: ");
                    String email = scanner.next();

                    usuarios.add(new User(nome, email));
                    break;

                case 2:
                    System.out.println("--Lista de Usuários--");
                    if (usuarios.isEmpty()) {
                        System.out.println("Nenhum usuário cadastrado");
                    } else {
                        for (int i = 0; i < usuarios.size(); i++) {
                            System.out.println(i + " - " + usuarios.get(i));
                        }
                    }
                    break;

                case 3:
                    System.out.println("Insira o Id do usuário para editar: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine();

                    if (idEditar >= 0 && idEditar < usuarios.size()) {
                        User user = usuarios.get(idEditar);

                        System.out.println("O que deseja editar?");
                        System.out.println("1 - Nome (Atual: " + user.getNome() + ")");
                        System.out.println("2 - Email (Atual: " + user.getEmail() + ")");
                        int subOpcao = scanner.nextInt();
                        scanner.nextLine();

                        switch (subOpcao){
                            case 1:
                                System.out.println("Novo nome: ");
                                user.setNome(scanner.nextLine());
                                System.out.println("Nome atualizado");
                                break;

                            case 2:
                                System.out.println("Novo email: ");
                                user.setEmail(scanner.nextLine());
                                System.out.println("Email atualizado");
                                break;
                            default:
                                System.out.println("Opção inválida");
                        }
                    } else {
                        System.out.println("Id Inválido");
                    }
                    break;

                case 4:
                    System.out.println("Insira o Id do usuário para excluir");
                    int idExcluir = scanner.nextInt();
                    if (idExcluir >= 0 && idExcluir < usuarios.size()) {
                        usuarios.remove(idExcluir);
                        System.out.println("Usuário excluído com sucesso!");
                    } else {
                        System.out.println("Id Inválido!");
                    }
                    break;

                default:
                    System.out.println("Saindo...");
                    break;

            }
        }
        scanner.close();

    }
}
