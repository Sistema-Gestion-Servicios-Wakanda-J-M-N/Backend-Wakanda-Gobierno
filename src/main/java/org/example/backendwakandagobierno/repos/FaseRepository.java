package org.example.backendwakandagobierno.repos;

import org.example.backendwakandagobierno.domain.proyectos.Fase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaseRepository extends JpaRepository<Fase, Long> {

    // Encuentra fases por estado
    //List<Fase> findByEstado(String estado);

    // Encuentra fases por proyecto
    //List<Fase> findByProyectoLocal_Id(Long proyectoLocalId);
}

