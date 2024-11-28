package org.example.backendwakandagobierno.domain.proyectos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Presupuestos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Presupuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private Double montoAsignado;

    @Column(nullable = false)
    private Double montoGastado;

    @ManyToOne
    @JoinColumn(name = "proyecto_id", nullable = false)
    private ProyectoLocal proyecto;
}

