package org.deloitte.Model;

import java.util.ArrayList;

public class Tarefa {

    public static ArrayList<Tarefa> tarefas = new ArrayList<>();
    public static long contadorId = 1;

    public long id;
    public String titulo;
    public Categoria categoria;
    public Status status;

    public Tarefa(String titulo, Categoria categoria, Status status) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.status = status;
    }

    public void cadastrar(){
        this.id = contadorId++;
        tarefas.add(this);
        System.out.println("Tarefa '" + this.titulo + "' salva com sucesso!");
    }

    public static void listar(){
        System.out.println("--Lista de Tarefas--");
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa criada");
        }
        for(Tarefa tarefa : tarefas){
            System.out.println("Id: " + tarefa.id +
                    " | Título: " + tarefa.titulo +
                    " | Status: " + tarefa.status +
                    " | Categoria: " + tarefa.categoria);
        }

    }

    public static Tarefa buscar(long idBusca){
        for(Tarefa tarefa : tarefas){
            if(tarefa.id == idBusca){
                return tarefa;
            }
        }
        return null;
    }

    public static void excluir(long idBusca){
        for(int i = 0; i < tarefas.size(); i++){
            if(tarefas.get(i).id == idBusca){
                tarefas.remove(i);
                System.out.println("Tarefa removida");
                return;
            }
        }
        System.out.println("Id não encontrado");
    }
}
