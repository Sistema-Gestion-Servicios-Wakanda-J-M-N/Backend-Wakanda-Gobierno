package org.example.backendwakandagobierno.service.sugerencias;

import jakarta.transaction.Transactional;
import org.example.backendwakandagobierno.domain.proyectos.ProyectoLocal;
import org.example.backendwakandagobierno.domain.sugerencias.GestorSugerencias;
import org.example.backendwakandagobierno.domain.sugerencias.Sugerencia;
import org.example.backendwakandagobierno.domain.usuarios.Usuario;
import org.example.backendwakandagobierno.model.sugerencias.SugerenciaDTO;
import org.example.backendwakandagobierno.repos.GestorSugerenciasRepository;
import org.example.backendwakandagobierno.repos.ProyectoLocalRepository;
import org.example.backendwakandagobierno.repos.SugerenciaRepository;
import org.example.backendwakandagobierno.repos.UsuarioRepository;
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
    private UsuarioRepository usuarioRepository; // Necesario para obtener usuarios existentes

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


    public List<SugerenciaDTO> findAllByProyectoId(Long proyectoId) {
        return sugerenciaRepository.findByProyectoId(proyectoId).stream()
                .map(sugerencia -> mapToDTO(sugerencia, new SugerenciaDTO()))
                .collect(Collectors.toList());
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


    // metodo con @Scheduled sin parámetros
    @Transactional
    @Scheduled(fixedRate = 10000) // Cada 10 segundos
    public void generateRandomSugerenciaTask() {
        Long proyectoId = obtenerUnProyectoIdAleatorio();
        Long usuarioId = obtenerUnUsuarioIdAleatorio();

        if (proyectoId != null && usuarioId != null) {
            System.out.println("Generando sugerencia para Proyecto ID: " + proyectoId + ", Usuario ID: " + usuarioId);
            generateRandomSugerencia(proyectoId, usuarioId);
        } else {
            System.out.println("No se encontraron Proyectos o Usuarios para generar sugerencias.");
        }
    }

    // metodo original para generar sugerencias
    @Transactional
    public void generateRandomSugerencia(Long proyectoId, Long usuarioId) {
        ProyectoLocal proyecto = proyectoLocalRepository.findById(proyectoId)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + proyectoId));
        GestorSugerencias gestor = proyecto.getGobernanzaDigital().getGestorSugerencias();
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));

        Sugerencia sugerencia = new Sugerencia();
        sugerencia.setDescripcion("Sugerencia generada aleatoriamente");
        sugerencia.setFechaEnvio(new Date());
        sugerencia.setEstado("ENVIADA");
        sugerencia.setUsuario(usuario);
        sugerencia.setProyecto(proyecto);
        sugerencia.setGestorSugerencias(gestor);

        proyecto.getSugerencias().add(sugerencia);
        gestor.getSugerencias().add(sugerencia);

        sugerenciaRepository.save(sugerencia);
        proyectoLocalRepository.save(proyecto);
        gestorSugerenciasRepository.save(gestor);

        System.out.println("Sugerencia generada exitosamente.");
    }

    // metodo helper para obtener un ProyectoLocal ID aleatorio
    private Long obtenerUnProyectoIdAleatorio() {
        return proyectoLocalRepository.findAll().stream()
                .map(ProyectoLocal::getId)
                .findAny()
                .orElse(null);
    }

    // metodo helper para obtener un Usuario ID aleatorio
    private Long obtenerUnUsuarioIdAleatorio() {
        return usuarioRepository.findAll().stream()
                .map(Usuario::getId)
                .findAny()
                .orElse(null);
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

