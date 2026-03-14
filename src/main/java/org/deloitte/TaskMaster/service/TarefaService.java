package org.deloitte.TaskMaster.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.deloitte.TaskMaster.config.validation.InterfaceIdValidation;
import org.deloitte.TaskMaster.config.validation.InterfaceDtoValidation;
import org.deloitte.TaskMaster.dto.TarefaDto;
import org.deloitte.TaskMaster.entity.Categoria;
import org.deloitte.TaskMaster.entity.Status;
import org.deloitte.TaskMaster.entity.Tarefa;
import org.deloitte.TaskMaster.mapper.TarefaMapper;
import org.deloitte.TaskMaster.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;
    private final List<InterfaceDtoValidation> dtoValidations;
    private final List<InterfaceIdValidation> idValidations;

    public List<TarefaDto> getAll(){
        return tarefaMapper
                .toDtoList(tarefaRepository.findAll());
    }

    public List<TarefaDto> getAllStatus(Status status) {
        List<Tarefa> tarefasStatus = tarefaRepository.findByStatus(status);
        return tarefaMapper.toDtoList(tarefasStatus);
    }

    public List<TarefaDto> getAllCategoria(Categoria categoria) {
        List<Tarefa> tarefasCategoria = tarefaRepository.findByCategoria(categoria);
        return tarefaMapper.toDtoList(tarefasCategoria);
    }

    public TarefaDto getById(Long id){
        idValidations.forEach(v -> v.validar(id));
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow();
        return tarefaMapper.toDto(tarefa);
    }

    public TarefaDto create(TarefaDto tarefaDto){

        dtoValidations.forEach(v ->
                v.validar(tarefaDto));

        Tarefa novaTarefa = tarefaMapper.toEntity(tarefaDto);
        return tarefaMapper.toDto(tarefaRepository.save(novaTarefa));

    }

    public TarefaDto update(Long id, TarefaDto tarefaDto){
        idValidations.forEach(v -> v.validar(id));
        dtoValidations.forEach(v -> v.validar(tarefaDto));

        Tarefa tarefaExistente = tarefaRepository.findById(id).orElseThrow();

        tarefaMapper.partialUpdate(tarefaDto, tarefaExistente);
        return tarefaMapper.toDto(tarefaRepository.save(tarefaExistente));

    }

    public void delete(Long id){
        idValidations.forEach(v -> v.validar(id));
        tarefaRepository.deleteById(id);
    }

    public void deleteAll(){
        tarefaRepository.deleteAll();
    }
}
