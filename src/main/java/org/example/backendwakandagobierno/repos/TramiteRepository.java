package org.example.backendwakandagobierno.repos;

import org.example.backendwakandagobierno.domain.tramites.Tramite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TramiteRepository extends JpaRepository<Tramite, Long> {

    // Encuentra trámites por tipo
    //List<Tramite> findByTipo(String tipo);

    // Encuentra trámites por usuario
    //List<Tramite> findByUsuario_Id(Long usuarioId);

    // Encuentra trámites por estado
    //List<Tramite> findByEstado(String estado);
}
