package org.example.backendwakandagobierno.domain.sugerencias;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "GestoresSugerencias")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GestorSugerencias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "gestorSugerencias", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sugerencia> sugerencias;

    @Column(nullable = false)
    private Date fechaCreacion;

    @Column(nullable = false)
    private String estado; // 'ACTIVO', 'INACTIVO'
}
