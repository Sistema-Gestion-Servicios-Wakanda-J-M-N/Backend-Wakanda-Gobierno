package org.example.backendwakandagobierno.service.sugerencias;

import org.example.backendwakandagobierno.domain.sugerencias.GestorSugerencias;
import org.example.backendwakandagobierno.model.sugerencias.GestorSugerenciasDTO;
import org.example.backendwakandagobierno.repos.GestorSugerenciasRepository;
import org.springframework.stereotype.Service;

@Service
public class GestorSugerenciasService {

    private final GestorSugerenciasRepository gestorSugerenciasRepository;

    public GestorSugerenciasService(final GestorSugerenciasRepository gestorSugerenciasRepository) {
        this.gestorSugerenciasRepository = gestorSugerenciasRepository;
    }

    public GestorSugerenciasDTO get(final Long id) {
        return gestorSugerenciasRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Gestor de sugerencias no encontrado con ID: " + id));
    }

    public Long create(final GestorSugerenciasDTO dto) {
        final GestorSugerencias gestor = mapToEntity(dto, new GestorSugerencias());
        return gestorSugerenciasRepository.save(gestor).getId();
    }

    private GestorSugerenciasDTO mapToDTO(final GestorSugerencias gestor) {
        GestorSugerenciasDTO dto = new GestorSugerenciasDTO();
        dto.setId(gestor.getId());
        dto.setEstado(gestor.getEstado());
        return dto;
    }

    private GestorSugerencias mapToEntity(final GestorSugerenciasDTO dto, final GestorSugerencias gestor) {
        gestor.setEstado(dto.getEstado());
        return gestor;
    }
}

