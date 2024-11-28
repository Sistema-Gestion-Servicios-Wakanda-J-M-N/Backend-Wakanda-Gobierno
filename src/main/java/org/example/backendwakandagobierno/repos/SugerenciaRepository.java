package org.example.backendwakandagobierno.repos;

import org.example.backendwakandagobierno.domain.sugerencias.Sugerencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SugerenciaRepository extends JpaRepository<Sugerencia, Long> {

    // Encuentra sugerencias por usuario
    //List<Sugerencia> findByUsuario_Id(Long usuarioId);

    // Encuentra sugerencias por estado
    //List<Sugerencia> findByEstado(String estado);
}

