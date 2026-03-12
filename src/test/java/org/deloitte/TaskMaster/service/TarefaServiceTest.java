package org.deloitte.TaskMaster.service;

import org.deloitte.TaskMaster.dto.TarefaDto;
import org.deloitte.TaskMaster.entity.*;
import org.deloitte.TaskMaster.mapper.TarefaMapper;
import org.deloitte.TaskMaster.repository.TarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @Mock
    private TarefaMapper tarefaMapper;

    @InjectMocks
    private TarefaService tarefaService;

    private Tarefa tarefa;
    private TarefaDto tarefaDto;

    @BeforeEach
    void setUp(){
        tarefa = new Tarefa(1L,"Estudar Testes", Categoria.ESTUDOS, Status.PENDENTE);
        tarefaDto = new TarefaDto("Estudar Testes", Categoria.ESTUDOS, Status.PENDENTE);
    }

    @Test
    @DisplayName("Deve retornar uma lista com todas as tarefas")
    void getAll_Success() {

        when(tarefaRepository.findAll()).thenReturn(List.of(tarefa));
        when(tarefaMapper.toDtoList(anyList())).thenReturn(List.of(tarefaDto));

        List<TarefaDto> resultado = tarefaService.getAll();
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(tarefaRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não houver tarefas")
    void getAll_Error() {
        when(tarefaRepository.findAll()).thenReturn(List.of());
        when(tarefaMapper.toDtoList(anyList())).thenReturn(List.of());

        List<TarefaDto> resultado = tarefaService.getAll();

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Deve filtrar tarefas por Status")
    void getAllStatus_Success() {
        when(tarefaRepository.findByStatus(any())).thenReturn(List.of(tarefa));

        when(tarefaMapper.toDtoList(anyList())).thenReturn(List.of(tarefaDto));

        List<TarefaDto> resultado = tarefaService.getAllStatus(Status.PENDENTE);
        assertNotNull(resultado);
        verify(tarefaRepository).findByStatus(Status.PENDENTE);
    }

    @Test
    @DisplayName("Deve filtrar tarefas por Categoria")
    void getAllCategoria_Success() {
        when(tarefaRepository.findByCategoria(any())).thenReturn(List.of(tarefa));
        when(tarefaMapper.toDtoList(anyList())).thenReturn(List.of(tarefaDto));

        List<TarefaDto> resultado = tarefaService.getAllCategoria(Categoria.ESTUDOS);

        assertNotNull(resultado);
        verify(tarefaRepository).findByCategoria(Categoria.ESTUDOS);
    }

    @Test
    @DisplayName("Deve retornar uma tarefa por ID com sucesso")
    void getById_Success() {
        when(tarefaRepository.findById(anyLong())).thenReturn(Optional.of(tarefa));
        when(tarefaMapper.toDto(any())).thenReturn(tarefaDto);

        TarefaDto resultado = tarefaService.getById(1L);

        assertNotNull(resultado);
        assertEquals("Estudar Testes", resultado.titulo());
        verify(tarefaRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção quando ID não existir")
    void getById_Error() {
        when(tarefaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> tarefaService.getById(99L));
    }

    @Test
    @DisplayName("Deve criar uma tarefa com sucesso")
    void create_Success() {
        when(tarefaRepository.findByTitulo(anyString())).thenReturn(Optional.empty());
        when(tarefaMapper.toEntity(any())).thenReturn(tarefa);
        when(tarefaRepository.save(any())).thenReturn(tarefa);
        when(tarefaMapper.toDto(any())).thenReturn(tarefaDto);

        TarefaDto criado = tarefaService.create(tarefaDto);

        assertNotNull(criado);
        verify(tarefaRepository).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar criar tarefa com título já existente")
    void create_Error(){
        when(tarefaRepository.findByTitulo(anyString())).thenReturn(Optional.of(tarefa));
        assertThrows(ResponseStatusException.class, () -> tarefaService.create(tarefaDto));
        verify(tarefaRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve atualizar tarefa com sucesso quando o ID existe")
    void update_Success() {
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(any())).thenReturn(tarefa);
        when(tarefaMapper.toDto(any())).thenReturn(tarefaDto);

        TarefaDto resultado = tarefaService.update(1L, tarefaDto);

        assertNotNull(resultado);
        verify(tarefaMapper).partialUpdate(any(), any());
        verify(tarefaRepository).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar atualizar ID inexistente")
    void update_Error(){
        when(tarefaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> tarefaService.update(99L, tarefaDto));
        verify(tarefaRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve deletar tarefa por ID com sucesso")
    void delete_Success() {
        when(tarefaRepository.existsById(1L)).thenReturn(true);

        assertDoesNotThrow(() -> tarefaService.delete(1L));

        verify(tarefaRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar ID que não existe")
    void delete_Error() {
        when(tarefaRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> tarefaService.delete(1L));
        verify(tarefaRepository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("Deve limpar a base de dados com sucesso")
    void deleteAll_Success() {
        assertDoesNotThrow(() -> tarefaService.deleteAll());
        verify(tarefaRepository).deleteAll();
    }

    @Test
    @DisplayName("Deve lançar erro ao falhar a comunicação com o banco no deleteAll")
    void deleteAll_Error(){
        doThrow(new RuntimeException("Erro de conexão")).when(tarefaRepository).deleteAll();
        assertThrows(RuntimeException.class, () -> tarefaService.deleteAll());

        verify(tarefaRepository, times(1)).deleteAll();
    }
}