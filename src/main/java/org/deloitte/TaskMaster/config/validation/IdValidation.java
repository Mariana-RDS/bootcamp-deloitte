package org.deloitte.TaskMaster.config.validation;

import lombok.RequiredArgsConstructor;
import org.deloitte.TaskMaster.repository.TarefaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class IdValidation implements InterfaceIdValidation {

    private final TarefaRepository tarefaRepository;

    @Override
    public void validar(Long id){
        if (!tarefaRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada");
        }
    }
}
