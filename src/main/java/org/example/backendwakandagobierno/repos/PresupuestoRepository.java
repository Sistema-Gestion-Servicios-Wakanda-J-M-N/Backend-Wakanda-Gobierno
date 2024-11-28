package org.example.backendwakandagobierno.repos;

import org.example.backendwakandagobierno.domain.proyectos.Presupuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresupuestoRepository extends JpaRepository<Presupuesto, Long> {

    // Encuentra presupuestos por proyecto local
    //List<Presupuesto> findByProyectoLocal_Id(Long proyectoLocalId);
}
