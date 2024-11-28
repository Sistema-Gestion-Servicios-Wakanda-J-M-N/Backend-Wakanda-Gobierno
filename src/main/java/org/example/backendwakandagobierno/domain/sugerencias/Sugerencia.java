package org.example.backendwakandagobierno.domain.sugerencias;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.backendwakandagobierno.domain.usuarios.Usuario;

import java.util.Date;

@Entity
@Table(name = "Sugerencias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sugerencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Date fechaEnvio;

    @Column(nullable = false)
    private String estado; // 'ENVIADA', 'REVISADA', 'APROBADA', 'RECHAZADA'

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
