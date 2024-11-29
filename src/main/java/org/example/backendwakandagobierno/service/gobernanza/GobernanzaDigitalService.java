package org.example.backendwakandagobierno.service.gobernanza;

import org.example.backendwakandagobierno.aop.anotaciones.Auditable;
import org.example.backendwakandagobierno.aop.anotaciones.Metrics;
import org.example.backendwakandagobierno.aop.anotaciones.Secure;
import org.example.backendwakandagobierno.domain.gobernanza.GobernanzaDigital;
import org.example.backendwakandagobierno.model.gobernanza.GobernanzaDigitalDTO;
import org.example.backendwakandagobierno.model.proyectos.ProyectoLocalDTO;
import org.example.backendwakandagobierno.repos.GobernanzaDigitalRepository;
import org.example.backendwakandagobierno.repos.ProyectoLocalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GobernanzaDigitalService {


    private final GobernanzaDigitalRepository gobernanzaDigitalRepository;
    private final ProyectoLocalRepository proyectoLocalRepository;


    public GobernanzaDigitalService(final GobernanzaDigitalRepository gobernanzaDigitalRepository,
                                    final ProyectoLocalRepository proyectoLocalRepository) {
        this.gobernanzaDigitalRepository = gobernanzaDigitalRepository;
        this.proyectoLocalRepository = proyectoLocalRepository;
    }



    public List<GobernanzaDigitalDTO> findAll() {
        return gobernanzaDigitalRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }



    public GobernanzaDigitalDTO get(final Long id) {
        return gobernanzaDigitalRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Gobernanza Digital no encontrada con ID: " + id));
    }


    @Metrics
    @Secure
    @Auditable
    // Nuevo: Listar todos los proyectos locales gestionados
    public List<ProyectoLocalDTO> listarProyectos(Long gobernanzaId) {
        GobernanzaDigital gobernanza = gobernanzaDigitalRepository.findById(gobernanzaId)
                .orElseThrow(() -> new RuntimeException("Gobernanza no encontrada con ID: " + gobernanzaId));

        return gobernanza.getProyectosLocales().stream()
                .map(proyecto -> {
                    ProyectoLocalDTO dto = new ProyectoLocalDTO();
                    dto.setId(proyecto.getId());
                    dto.setNombre(proyecto.getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
    }



    private GobernanzaDigitalDTO mapToDTO(final GobernanzaDigital gobernanza) {
        GobernanzaDigitalDTO dto = new GobernanzaDigitalDTO();
        dto.setId(gobernanza.getId());
        dto.setGestorSugerenciasId(gobernanza.getGestorSugerencias().getId());
        return dto;
    }



    private GobernanzaDigital mapToEntity(final GobernanzaDigitalDTO dto, final GobernanzaDigital gobernanza) {
        return gobernanza;
    }
}


