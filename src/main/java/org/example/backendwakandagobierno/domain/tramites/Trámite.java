package org.example.backendwakandagobierno.domain.tramites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backendwakandagobierno.domain.usuarios.Usuario;

import java.util.Date;

@Entity
@Table(name = "Tramites")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trámite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipo; // 'REGISTRO', 'PERMISO', 'SOLICITUD'

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String estado; // 'EN PROCESO', 'FINALIZADO', 'RECHAZADO'

    @Column(nullable = false)
    private Date fechaSolicitud;

    private Date fechaResolucion;
}

