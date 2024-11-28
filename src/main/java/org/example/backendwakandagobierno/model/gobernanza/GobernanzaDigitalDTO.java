package org.example.backendwakandagobierno.model.gobernanza;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GobernanzaDigitalDTO {

    private Long id;

    @NotNull
    private List<Long> tramitesIds;

    @NotNull
    private List<Long> proyectosLocalesIds;

    private Long gestorSugerenciasId;
}

