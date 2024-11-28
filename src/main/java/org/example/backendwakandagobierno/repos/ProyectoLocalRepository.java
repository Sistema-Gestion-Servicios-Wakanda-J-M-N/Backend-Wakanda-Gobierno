package org.example.backendwakandagobierno.repos;

import org.example.backendwakandagobierno.domain.proyectos.ProyectoLocal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoLocalRepository extends JpaRepository<ProyectoLocal, Long> {

    // Encuentra proyectos por estado
    //List<ProyectoLocal> findByEstado(String estado);
}

