package org.example.backendwakandagobierno.model.proyectos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FaseDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String nombre;

    @NotNull
    @Size(max = 1000)
    private String descripcion;

    @NotNull
    private Date fechaInicio;

    @NotNull
    private Date fechaFin;

    @NotNull
    private String estado; // 'PENDIENTE', 'EN PROGRESO', 'FINALIZADA'

    private Long presupuestoId;

    private Long proyectoLocalId;
}

