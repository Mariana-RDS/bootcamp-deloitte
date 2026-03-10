package org.deloitte.TaskMaster.repository;

import org.deloitte.TaskMaster.model.Categoria;
import org.deloitte.TaskMaster.model.Status;
import org.deloitte.TaskMaster.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    Optional<Tarefa> findByTitulo(String titulo);

    List<Tarefa> findByStatus(Status status);
    List<Tarefa> findByCategoria(Categoria categoria);
}
