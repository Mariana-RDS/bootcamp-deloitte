package org.deloitte.TaskMaster.controller;

import lombok.RequiredArgsConstructor;
import org.deloitte.TaskMaster.dto.TarefaDto;
import org.deloitte.TaskMaster.entity.Status;
import org.deloitte.TaskMaster.service.TarefaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaViewController {

    private final TarefaService tarefaService;

    @GetMapping
    public String exibirQuadro(Model model){
        List<TarefaDto> tarefas = tarefaService.getAll();
        model.addAttribute("tarefas", tarefas);
        return "index";
    }

    @PostMapping("/salvar")
    public String salvarTarefa(@ModelAttribute TarefaDto tarefaDto){
        tarefaService.create(tarefaDto);
        return "redirect:/tarefas";
    }

    @PutMapping("/editar/{id}")
    public String editarTarefa(@PathVariable Long id, @ModelAttribute TarefaDto tarefaDto){
        tarefaService.update(id, tarefaDto);
        return "redirect:/tarefas";
    }

    @PostMapping("/status/{id}")
    public String atualizarStatus(@PathVariable Long id, @RequestParam Status novoStatus){
        TarefaDto dto = tarefaService.getById(id);
        TarefaDto dtoAtualizado = new TarefaDto(
                dto.id(),
                dto.titulo(),
                dto.categoria(),
                novoStatus
        );
        tarefaService.update(id, dtoAtualizado);
        return "redirect:/tarefas";

    }

    @DeleteMapping("/excluir/{id}")
    public String excluirTarefa(@PathVariable Long id){
        tarefaService.delete(id);
        return "redirect:/tarefas";
    }
}
