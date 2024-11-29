package org.example.backendwakandagobierno.service.proyectos;

import org.example.backendwakandagobierno.domain.proyectos.ProyectoLocal;
import org.example.backendwakandagobierno.model.proyectos.ProyectoLocalDTO;
import org.example.backendwakandagobierno.repos.ProyectoLocalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProyectoLocalService {

    private final ProyectoLocalRepository proyectoLocalRepository;

    public ProyectoLocalService(final ProyectoLocalRepository proyectoLocalRepository) {
        this.proyectoLocalRepository = proyectoLocalRepository;
    }

    public List<ProyectoLocalDTO> findAll() {
        return proyectoLocalRepository.findAll().stream()
                .map(proyecto -> mapToDTO(proyecto, new ProyectoLocalDTO()))
                .collect(Collectors.toList());
    }

    public ProyectoLocalDTO get(final Long id) {
        return proyectoLocalRepository.findById(id)
                .map(proyecto -> mapToDTO(proyecto, new ProyectoLocalDTO()))
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + id));
    }

    public Long create(final ProyectoLocalDTO proyectoDTO) {
        ProyectoLocal proyecto = mapToEntity(proyectoDTO, new ProyectoLocal());
        return proyectoLocalRepository.save(proyecto).getId();
    }

    private ProyectoLocalDTO mapToDTO(final ProyectoLocal proyecto, final ProyectoLocalDTO dto) {
        dto.setId(proyecto.getId());
        dto.setNombre(proyecto.getNombre());
        dto.setDescripcion(proyecto.getDescripcion());
        dto.setFechaInicio(proyecto.getFechaInicio());
        dto.setFechaFin(proyecto.getFechaFin());
        dto.setEstado(proyecto.getEstado());
        return dto;
    }

    private ProyectoLocal mapToEntity(final ProyectoLocalDTO dto, final ProyectoLocal proyecto) {
        proyecto.setNombre(dto.getNombre());
        proyecto.setDescripcion(dto.getDescripcion());
        proyecto.setFechaInicio(dto.getFechaInicio());
        proyecto.setFechaFin(dto.getFechaFin());
        proyecto.setEstado(dto.getEstado());
        return proyecto;
    }
}

