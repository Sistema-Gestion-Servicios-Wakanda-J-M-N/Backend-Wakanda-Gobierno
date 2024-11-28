package org.example.backendwakandagobierno.model.proyectos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PresupuestoDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String categoria;

    @NotNull
    private Double montoAsignado;

    @NotNull
    private Double montoGastado;

    private Long proyectoLocalId;
}

