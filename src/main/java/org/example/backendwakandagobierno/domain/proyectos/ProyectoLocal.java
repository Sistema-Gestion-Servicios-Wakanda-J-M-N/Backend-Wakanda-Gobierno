package org.example.backendwakandagobierno.domain.proyectos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backendwakandagobierno.domain.gobernanza.GobernanzaDigital;
import org.example.backendwakandagobierno.domain.sugerencias.Sugerencia;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ProyectosLocales")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProyectoLocal {

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
    private String estado; // 'PROPUESTO', 'EN PROGRESO', 'COMPLETADO'

    @ManyToOne
    @JoinColumn(name = "gobernanza_digital_id", nullable = false)
    private GobernanzaDigital gobernanzaDigital;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Fase> fases;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sugerencia> sugerencias;
}

