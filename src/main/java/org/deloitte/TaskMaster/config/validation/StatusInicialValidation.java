package org.deloitte.TaskMaster.config.validation;

import lombok.RequiredArgsConstructor;
import org.deloitte.TaskMaster.dto.TarefaDto;
import org.deloitte.TaskMaster.entity.Status;
import org.deloitte.TaskMaster.repository.TarefaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class StatusInicialValidation implements InterfaceDtoValidation{

    private final TarefaRepository tarefaRepository;

    @Override
    public void validar(TarefaDto dto) {
        if (dto.status() == Status.CONCLUIDA) {

            if (dto.id() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Uma nova tarefa não pode ser criada já como concluída");
            }

            if (!tarefaRepository.existsById(dto.id())) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
            }

        }
    }
}
