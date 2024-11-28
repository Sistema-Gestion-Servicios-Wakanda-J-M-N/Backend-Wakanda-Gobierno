package org.example.backendwakandagobierno.repos;

import org.example.backendwakandagobierno.domain.sugerencias.GestorSugerencias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GestorSugerenciasRepository extends JpaRepository<GestorSugerencias, Long> {

    // Encuentra gestores activos
    //List<GestorSugerencias> findByEstado(String estado);
}

