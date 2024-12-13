package org.example.backendwakandagobierno.domain.gobernanza;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backendwakandagobierno.domain.proyectos.ProyectoLocal;
import org.example.backendwakandagobierno.domain.sugerencias.GestorSugerencias;

import java.util.List;

@Entity
@Table(name = "GobernanzaDigital")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GobernanzaDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "gobernanzaDigital", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProyectoLocal> proyectosLocales;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gestor_sugerencias_id", nullable = false)
    private GestorSugerencias gestorSugerencias;
}

