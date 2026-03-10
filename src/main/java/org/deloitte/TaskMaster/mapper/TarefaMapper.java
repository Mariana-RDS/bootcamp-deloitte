package org.deloitte.TaskMaster.mapper;

import org.deloitte.TaskMaster.dto.TarefaDto;
import org.deloitte.TaskMaster.model.Tarefa;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

    TarefaDto toDto(Tarefa tarefa);
    List<TarefaDto> toDtoList(List<Tarefa> tarefas);

    Tarefa toEntity(TarefaDto dto);
    List<Tarefa> toEntityList(List<TarefaDto> dtos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Tarefa partialUpdate(TarefaDto tarefaDto, @MappingTarget Tarefa tarefa);
}
