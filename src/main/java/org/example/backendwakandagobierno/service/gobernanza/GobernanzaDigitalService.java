package org.example.backendwakandagobierno.service.gobernanza;

import org.example.backendwakandagobierno.domain.gobernanza.GobernanzaDigital;
import org.example.backendwakandagobierno.domain.proyectos.ProyectoLocal;
import org.example.backendwakandagobierno.domain.sugerencias.GestorSugerencias;
import org.example.backendwakandagobierno.model.gobernanza.GobernanzaDigitalDTO;
import org.example.backendwakandagobierno.model.proyectos.ProyectoLocalDTO;
import org.example.backendwakandagobierno.repos.GobernanzaDigitalRepository;
import org.example.backendwakandagobierno.repos.ProyectoLocalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GobernanzaDigitalService {

    private final GobernanzaDigitalRepository gobernanzaRepo;
    private final ProyectoLocalRepository proyectoRepo;

    public GobernanzaDigitalService(GobernanzaDigitalRepository gobernanzaRepo, ProyectoLocalRepository proyectoRepo) {
        this.gobernanzaRepo = gobernanzaRepo;
        this.proyectoRepo = proyectoRepo;
    }

    public List<GobernanzaDigitalDTO> findAll() {
        return gobernanzaRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public GobernanzaDigitalDTO get(Long id) {
        return gobernanzaRepo.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Gobernanza no encontrada"));
    }

    @Transactional
    public Long create(GobernanzaDigitalDTO dto) {
        GobernanzaDigital gobernanza = new GobernanzaDigital();

        // Crear GestorSugerencias y asignar valor para 'estado'
        GestorSugerencias gestorSugerencias = new GestorSugerencias();
        gestorSugerencias.setEstado("ACTIVO");
        gestorSugerencias.setFechaCreacion(new Date());

        gobernanza.setGestorSugerencias(gestorSugerencias);

        gobernanzaRepo.save(gobernanza);
        return gobernanza.getId();
    }

    @Transactional
    public void delete(Long id) {
        GobernanzaDigital gobernanza = gobernanzaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Gobernanza no encontrada"));
        gobernanzaRepo.delete(gobernanza);
    }

    public List<ProyectoLocalDTO> listarProyectos(Long gobernanzaId) {
        GobernanzaDigital gobernanza = gobernanzaRepo.findById(gobernanzaId)
                .orElseThrow(() -> new RuntimeException("Gobernanza no encontrada"));
        return gobernanza.getProyectosLocales().stream().map(this::toProyectoDTO).collect(Collectors.toList());
    }

    private GobernanzaDigitalDTO toDTO(GobernanzaDigital g) {
        GobernanzaDigitalDTO dto = new GobernanzaDigitalDTO();
        dto.setId(g.getId());
        return dto;
    }

    private ProyectoLocalDTO toProyectoDTO(ProyectoLocal p) {
        ProyectoLocalDTO dto = new ProyectoLocalDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        return dto;
    }
}
