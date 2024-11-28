package org.example.backendwakandagobierno.model.sugerencias;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SugerenciaDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String descripcion;

    @NotNull
    private Date fechaEnvio;

    @NotNull
    private String estado; // 'ENVIADA', 'REVISADA', 'APROBADA', 'RECHAZADA'

    private Long usuarioId;

    private Long gestorSugerenciasId;
}

