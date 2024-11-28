package org.example.backendwakandagobierno.repos;

import org.example.backendwakandagobierno.domain.usuarios.Credenciales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredencialesRepository extends JpaRepository<Credenciales, Long> {

    // Encuentra credenciales por correo
    //Optional<Credenciales> findByCorreo(String correo);
}
