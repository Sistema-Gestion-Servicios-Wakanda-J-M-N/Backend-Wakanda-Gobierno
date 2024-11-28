package org.example.backendwakandagobierno.repos;

import org.example.backendwakandagobierno.domain.gobernanza.GobernanzaDigital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GobernanzaDigitalRepository extends JpaRepository<GobernanzaDigital, Long> {

    // Encuentra gobernanza digital por proyecto local
    //Optional<GobernanzaDigital> findByProyectosLocales_Id(Long proyectoLocalId);

    // Encuentra gobernanza digital por gestor de sugerencias
    //Optional<GobernanzaDigital> findByGestorSugerencias_Id(Long gestorSugerenciasId);
}
