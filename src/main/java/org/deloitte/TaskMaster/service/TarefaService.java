package org.deloitte.TaskMaster.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.deloitte.TaskMaster.dto.TarefaDto;
import org.deloitte.TaskMaster.mapper.TarefaMapper;
import org.deloitte.TaskMaster.model.Categoria;
import org.deloitte.TaskMaster.model.Status;
import org.deloitte.TaskMaster.model.Tarefa;
import org.deloitte.TaskMaster.repository.TarefaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;

    public List<TarefaDto> getAll(){
        return tarefaMapper
                .toDtoList(tarefaRepository.findAll());
    }

    public List<TarefaDto> getAllStatus(Status status) {
        List<Tarefa> tarefasStatus = tarefaRepository.findByStatus(status);
        log.info("Buscando tarefas com status: {}", status);

        return tarefaMapper.toDtoList(tarefasStatus);
    }

    public List<TarefaDto> getAllCategoria(Categoria categoria) {
        log.info("Buscando tarefas da categoria: {}", categoria);
        List<Tarefa> tarefasCategoria = tarefaRepository.findByCategoria(categoria);

        return tarefaMapper.toDtoList(tarefasCategoria);
    }

    public TarefaDto getById(Long id){
        return tarefaRepository.findById(id)
                .map(tarefaMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
    }

    public TarefaDto create(TarefaDto tarefaDto){
        if (tarefaRepository.findByTitulo(tarefaDto.titulo()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe uma tarefa com este título");
        }else{
            Tarefa novaTarefa = tarefaMapper.toEntity(tarefaDto);
            return tarefaMapper.toDto(tarefaRepository.save(novaTarefa));
        }
    }

    public TarefaDto update(Long id, TarefaDto tarefaDto){
        return tarefaRepository.findById(id)
                .map(tarefa -> {
                    tarefaMapper.partialUpdate(tarefaDto, tarefa);
                    return tarefaMapper.toDto(tarefaRepository.save(tarefa));
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
    }

    public void delete(Long id){
        if (!tarefaRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado");
        }
        tarefaRepository.deleteById(id);
    }

    public void deleteAll(){
        tarefaRepository.deleteAll();
    }
}
