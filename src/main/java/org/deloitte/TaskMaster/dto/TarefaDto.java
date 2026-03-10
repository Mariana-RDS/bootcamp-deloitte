package org.deloitte.TaskMaster.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;
import org.deloitte.TaskMaster.entity.Categoria;
import org.deloitte.TaskMaster.entity.Status;

import java.io.Serializable;

public record TarefaDto(

        @NotBlank(message = "O título é obrigatório")
        @Size(max = 45)
        String titulo,

        @NotNull
        Categoria categoria,

        @NotNull
        Status status
) implements Serializable {}
