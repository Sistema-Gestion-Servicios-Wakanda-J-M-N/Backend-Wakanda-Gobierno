package org.example.backendwakandagobierno.repos;

import org.example.backendwakandagobierno.domain.tramites.Trámite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrámiteRepository extends JpaRepository<Trámite, Long> {

    // Encuentra trámites por tipo
    //List<Trámite> findByTipo(String tipo);

    // Encuentra trámites por usuario
    //List<Trámite> findByUsuario_Id(Long usuarioId);

    // Encuentra trámites por estado
    //List<Trámite> findByEstado(String estado);
}
