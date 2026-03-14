package org.deloitte.TaskMaster.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;
import org.deloitte.TaskMaster.entity.Categoria;
import org.deloitte.TaskMaster.entity.Status;

import java.io.Serializable;

public record TarefaDto(

        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        @JsonProperty(access = JsonProperty.Access.READ_WRITE)
        Long id,

        @NotBlank(message = "O título é obrigatório")
        @Size(max = 45)
        String titulo,

        @NotNull
        Categoria categoria,

        @NotNull
        Status status
) implements Serializable {}
