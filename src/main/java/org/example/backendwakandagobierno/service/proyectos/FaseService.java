package org.example.backendwakandagobierno.service.proyectos;

import org.example.backendwakandagobierno.domain.proyectos.Fase;
import org.example.backendwakandagobierno.model.proyectos.FaseDTO;
import org.example.backendwakandagobierno.repos.FaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FaseService {

    private final FaseRepository faseRepository;

    public FaseService(final FaseRepository faseRepository) {
        this.faseRepository = faseRepository;
    }

    public List<FaseDTO> findAll() {
        return faseRepository.findAll().stream()
                .map(fase -> mapToDTO(fase, new FaseDTO()))
                .collect(Collectors.toList());
    }

    public FaseDTO get(final Long id) {
        return faseRepository.findById(id)
                .map(fase -> mapToDTO(fase, new FaseDTO()))
                .orElseThrow(() -> new RuntimeException("Fase no encontrada con ID: " + id));
    }

    public Long create(final FaseDTO faseDTO) {
        final Fase fase = mapToEntity(faseDTO, new Fase());
        return faseRepository.save(fase).getId();
    }

    private FaseDTO mapToDTO(final Fase fase, final FaseDTO dto) {
        dto.setId(fase.getId());
        dto.setNombre(fase.getNombre());
        dto.setDescripcion(fase.getDescripcion());
        dto.setFechaInicio(fase.getFechaInicio());
        dto.setFechaFin(fase.getFechaFin());
        dto.setEstado(fase.getEstado());
        return dto;
    }

    private Fase mapToEntity(final FaseDTO dto, final Fase fase) {
        fase.setNombre(dto.getNombre());
        fase.setDescripcion(dto.getDescripcion());
        fase.setFechaInicio(dto.getFechaInicio());
        fase.setFechaFin(dto.getFechaFin());
        fase.setEstado(dto.getEstado());
        return fase;
    }
}

