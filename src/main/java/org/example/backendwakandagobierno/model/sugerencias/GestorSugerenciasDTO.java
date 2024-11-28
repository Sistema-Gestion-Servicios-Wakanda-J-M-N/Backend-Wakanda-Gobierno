package org.example.backendwakandagobierno.model.sugerencias;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class GestorSugerenciasDTO {

    private Long id;

    @NotNull
    private Date fechaCreacion;

    @NotNull
    private String estado; // 'ACTIVO', 'INACTIVO'

    private List<Long> sugerenciasIds;
}

