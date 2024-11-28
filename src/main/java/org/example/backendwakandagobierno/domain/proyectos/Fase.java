package org.example.backendwakandagobierno.domain.proyectos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "Fases")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    private Date fechaInicio;

    private Date fechaFin;

    @Column(nullable = false)
    private String estado; // 'PENDIENTE', 'EN PROGRESO', 'FINALIZADA'

    @ManyToOne
    @JoinColumn(name = "proyecto_id", nullable = false)
    private ProyectoLocal proyecto;
}

