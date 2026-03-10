package org.deloitte.TaskMaster.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.deloitte.TaskMaster.dto.TarefaDto;
import org.deloitte.TaskMaster.model.Categoria;
import org.deloitte.TaskMaster.model.Status;
import org.deloitte.TaskMaster.service.TarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    @Operation(summary = "Listar todas as tarefas")
    @GetMapping
    public ResponseEntity<List<TarefaDto>> getAllTarefas(){
        List<TarefaDto> dtos = tarefaService.getAll();
        return dtos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Listar todas as tarefas por Status")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TarefaDto>> getByStatus(@PathVariable Status status){
        return ResponseEntity.ok(tarefaService.getAllStatus(status));
    }

    @Operation(summary = "Listar todas as tarefas por categoria")
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<TarefaDto>> getByCategoria(@PathVariable Categoria categoria){
        return ResponseEntity.ok(tarefaService.getAllCategoria(categoria));
    }

    @Operation(summary = "Buscar tarefa por ID")
    @GetMapping("/{id}")
    public ResponseEntity<TarefaDto> getTarefaById(@PathVariable("id") Long id){
        return ResponseEntity.ok(tarefaService.getById(id));
    }

    @Operation(summary = "Criar uma nova tarefa")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TarefaDto createTarefa(@Valid @RequestBody TarefaDto tarefaDto){
        return tarefaService.create(tarefaDto);
    }

    @Operation(summary = "Editar tarefa")
    @PutMapping("/{id}")
    public ResponseEntity<TarefaDto> updateTarefa(@PathVariable("id") Long id, @Valid @RequestBody TarefaDto tarefaDto){
        return ResponseEntity.ok(tarefaService.update(id, tarefaDto));
    }

    @Operation(summary = "Deletar tarefa por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<TarefaDto> deleteTarefa(@PathVariable("id") Long id){
        tarefaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletar todas as tarefas")
    @DeleteMapping
    public ResponseEntity<TarefaDto> deleteAllTarefas(){
        tarefaService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
