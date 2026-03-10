package org.deloitte.TaskMaster.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;
import org.deloitte.TaskMaster.model.Categoria;
import org.deloitte.TaskMaster.model.Status;

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
