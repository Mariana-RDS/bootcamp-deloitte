package org.deloitte.TaskMaster.repository;

import jakarta.persistence.EntityManager;
import org.deloitte.TaskMaster.dto.TarefaDto;
import org.deloitte.TaskMaster.entity.Categoria;
import org.deloitte.TaskMaster.entity.Status;
import org.deloitte.TaskMaster.entity.Tarefa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class TarefaRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    TarefaRepository tarefaRepository;

    @Test
    @DisplayName("Deve retornar com título se existir no banco")
    void findByTitulo_Success() {
        String titulo = "Estudar Testes";
        TarefaDto tarefaDto = new TarefaDto("Estudar Testes", Categoria.ESTUDOS, Status.PENDENTE);
        this.createTarefa(tarefaDto);

        Optional<Tarefa> resultado =  this.tarefaRepository.findByTitulo(titulo);
        assertThat(resultado.isPresent());
        assertThat(resultado.get().getTitulo()).isEqualTo(titulo);
    }

    @Test
    @DisplayName("Não deve retornar com título se não existir no banco")
    void findByTitulo_Error() {
        String titulo = "Estudar Testes";

        Optional<Tarefa> resultado =  this.tarefaRepository.findByTitulo(titulo);
        assertThat(resultado.isEmpty());
    }

    @Test
    @DisplayName("Deve retornar com tarefa por status")
    void findByStatus_Success() {
        Status status = Status.PENDENTE;
        TarefaDto tarefaDto = new TarefaDto("Estudar Testes", Categoria.ESTUDOS, Status.PENDENTE);
        this.createTarefa(tarefaDto);


        List<Tarefa> resultado =  this.tarefaRepository.findByStatus(status);
        assertThat(resultado).isNotEmpty();
        assertThat(resultado.getFirst().getStatus()).isEqualTo(status);
    }

    @Test
    @DisplayName("Não deve retornar com tarefa por status")
    void findByStatus_Error() {
        Status status = Status.PENDENTE;

        List<Tarefa> resultado =  this.tarefaRepository.findByStatus(status);
        assertThat(resultado).isEmpty();
    }

    @Test
    @DisplayName("Deve retornar com tarefa por categoria")
    void findByCategoria_Success() {
        Categoria categoria = Categoria.ESTUDOS;
        TarefaDto tarefaDto = new TarefaDto("Estudar Testes", Categoria.ESTUDOS, Status.PENDENTE);
        this.createTarefa(tarefaDto);


        List<Tarefa> resultado =  this.tarefaRepository.findByCategoria(categoria);
        assertThat(resultado).isNotEmpty();
        assertThat(resultado.getFirst().getCategoria()).isEqualTo(categoria);
    }

    @Test
    @DisplayName("Não deve retornar com tarefa por categoria")
    void findByCategoria_Error() {
        Categoria categoria = Categoria.ESTUDOS;

        List<Tarefa> resultado =  this.tarefaRepository.findByCategoria(categoria);
        assertThat(resultado).isEmpty();
    }

    private Tarefa createTarefa(TarefaDto tarefaDto){
        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setTitulo(tarefaDto.titulo());
        novaTarefa.setCategoria(tarefaDto.categoria());
        novaTarefa.setStatus(tarefaDto.status());

        this.entityManager.persist(novaTarefa);
        this.entityManager.flush();
        return novaTarefa;
    }
}