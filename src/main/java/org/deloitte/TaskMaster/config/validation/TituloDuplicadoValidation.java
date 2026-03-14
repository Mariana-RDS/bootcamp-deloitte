package org.deloitte.TaskMaster.config.validation;

import lombok.RequiredArgsConstructor;
import org.deloitte.TaskMaster.dto.TarefaDto;
import org.deloitte.TaskMaster.repository.TarefaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class TituloDuplicadoValidation implements InterfaceDtoValidation {

    private final TarefaRepository tarefaRepository;

    @Override
    public void validar(TarefaDto tarefaDto){
        tarefaRepository.findByTitulo(tarefaDto.titulo()).ifPresent(tarefaExistente -> {
            if (tarefaDto.id() == null || !tarefaExistente.getId().equals(tarefaDto.id())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Título já existe");
            }
        });
    }
}
