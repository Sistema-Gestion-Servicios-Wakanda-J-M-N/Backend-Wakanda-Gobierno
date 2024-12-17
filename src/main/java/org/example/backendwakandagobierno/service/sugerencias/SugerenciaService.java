package org.example.backendwakandagobierno.service.sugerencias;

import org.example.backendwakandagobierno.domain.proyectos.ProyectoLocal;
import org.example.backendwakandagobierno.domain.sugerencias.GestorSugerencias;
import org.example.backendwakandagobierno.domain.sugerencias.Sugerencia;
import org.example.backendwakandagobierno.model.sugerencias.SugerenciaDTO;
import org.example.backendwakandagobierno.repos.GestorSugerenciasRepository;
import org.example.backendwakandagobierno.repos.ProyectoLocalRepository;
import org.example.backendwakandagobierno.repos.SugerenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SugerenciaService {

    private final SugerenciaRepository sugerenciaRepository;
    private final ProyectoLocalRepository proyectoLocalRepository;
    private final GestorSugerenciasRepository gestorSugerenciasRepository;

    @Autowired
    public SugerenciaService(final SugerenciaRepository sugerenciaRepository,
                             final ProyectoLocalRepository proyectoLocalRepository,
                             final GestorSugerenciasRepository gestorSugerenciasRepository) {
        this.sugerenciaRepository = sugerenciaRepository;
        this.proyectoLocalRepository = proyectoLocalRepository;
        this.gestorSugerenciasRepository = gestorSugerenciasRepository;
    }


    public List<SugerenciaDTO> findAll() {
        return sugerenciaRepository.findAll().stream()
                .map(sugerencia -> mapToDTO(sugerencia, new SugerenciaDTO()))
                .collect(Collectors.toList());
    }


    public SugerenciaDTO get(final Long id) {
        return sugerenciaRepository.findById(id)
                .map(sugerencia -> mapToDTO(sugerencia, new SugerenciaDTO()))
                .orElseThrow(() -> new RuntimeException("Sugerencia no encontrada con ID: " + id));
    }


    public Long create(final SugerenciaDTO dto) {
        final Sugerencia sugerencia = mapToEntity(dto, new Sugerencia());
        return sugerenciaRepository.save(sugerencia).getId();
    }



    public Long createSugerenciaForProyecto(Long proyectoId, SugerenciaDTO dto) {
        ProyectoLocal proyecto = proyectoLocalRepository.findById(proyectoId)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + proyectoId));
        GestorSugerencias gestor = proyecto.getGobernanzaDigital().getGestorSugerencias();

        Sugerencia sugerencia = mapToEntity(dto, new Sugerencia());
        sugerencia.setProyecto(proyecto);
        sugerencia.setGestorSugerencias(gestor);

        proyecto.getSugerencias().add(sugerencia);
        gestor.getSugerencias().add(sugerencia);

        sugerenciaRepository.save(sugerencia);
        proyectoLocalRepository.save(proyecto);
        gestorSugerenciasRepository.save(gestor);

        return sugerencia.getId();
    }


    @Scheduled(fixedRate = 10000)
    public void generateRandomSugerencia() {
        Random random = new Random();
        SugerenciaDTO dto = new SugerenciaDTO();
        dto.setDescripcion("Descripcion " + random.nextInt(1000));
        dto.setFechaEnvio(new Date());
        dto.setEstado("ENVIADA");
        dto.setUsuarioId(null); // Asignar un usuario válido si es necesario

        // Asignar un proyecto válido
        List<ProyectoLocal> proyectos = proyectoLocalRepository.findAll();
        if (!proyectos.isEmpty()) {
            ProyectoLocal proyecto = proyectos.get(random.nextInt(proyectos.size()));
            createSugerenciaForProyecto(proyecto.getId(), dto);
        }
    }



    private SugerenciaDTO mapToDTO(final Sugerencia sugerencia, final SugerenciaDTO dto) {
        dto.setId(sugerencia.getId());
        dto.setDescripcion(sugerencia.getDescripcion());
        dto.setFechaEnvio(sugerencia.getFechaEnvio());
        dto.setEstado(sugerencia.getEstado());
        dto.setUsuarioId(sugerencia.getUsuario() != null ? sugerencia.getUsuario().getId() : null);
        return dto;
    }



    private Sugerencia mapToEntity(final SugerenciaDTO dto, final Sugerencia sugerencia) {
        sugerencia.setDescripcion(dto.getDescripcion());
        sugerencia.setFechaEnvio(dto.getFechaEnvio());
        sugerencia.setEstado(dto.getEstado());
        return sugerencia;
    }
}

