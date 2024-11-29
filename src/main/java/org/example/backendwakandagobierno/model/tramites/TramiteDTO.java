package org.example.backendwakandagobierno.model.tramites;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TramiteDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String tipo; // 'REGISTRO', 'PERMISO', 'SOLICITUD'

    @NotNull
    private String estado; // 'EN PROCESO', 'FINALIZADO', 'RECHAZADO'

    @NotNull
    private Date fechaSolicitud;

    private Date fechaResolucion;

    private Long usuarioId;
}

