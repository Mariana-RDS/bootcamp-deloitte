package org.deloitte.TaskMaster.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name="titulo", length = 45, nullable = false)
    public String titulo;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    public Categoria categoria;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    public Status status;

}
