package org.example.backendwakandagobierno.service.gobernanza;

import org.example.backendwakandagobierno.domain.gobernanza.GobernanzaDigital;
import org.example.backendwakandagobierno.model.gobernanza.GobernanzaDigitalDTO;
import org.example.backendwakandagobierno.repos.GobernanzaDigitalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GobernanzaDigitalService {

    private final GobernanzaDigitalRepository gobernanzaDigitalRepository;

    public GobernanzaDigitalService(final GobernanzaDigitalRepository gobernanzaDigitalRepository) {
        this.gobernanzaDigitalRepository = gobernanzaDigitalRepository;
    }

    public List<GobernanzaDigitalDTO> findAll() {
        return gobernanzaDigitalRepository.findAll().stream()
                .map(gobernanza -> mapToDTO(gobernanza, new GobernanzaDigitalDTO()))
                .collect(Collectors.toList());
    }

    public GobernanzaDigitalDTO get(final Long id) {
        return gobernanzaDigitalRepository.findById(id)
                .map(gobernanza -> mapToDTO(gobernanza, new GobernanzaDigitalDTO()))
                .orElseThrow(() -> new RuntimeException("Gobernanza Digital no encontrada con ID: " + id));
    }

    public Long create(final GobernanzaDigitalDTO dto) {
        final GobernanzaDigital gobernanza = mapToEntity(dto, new GobernanzaDigital());
        return gobernanzaDigitalRepository.save(gobernanza).getId();
    }

    private GobernanzaDigitalDTO mapToDTO(final GobernanzaDigital gobernanza, final GobernanzaDigitalDTO dto) {
        dto.setId(gobernanza.getId());
        dto.setProyectosLocalesIds(
                gobernanza.getProyectosLocales().stream().map(proyecto -> proyecto.getId()).collect(Collectors.toList())
        );
        dto.setGestorSugerenciasId(
                gobernanza.getGestorSugerencias() != null ? gobernanza.getGestorSugerencias().getId() : null
        );
        return dto;
    }

    private GobernanzaDigital mapToEntity(final GobernanzaDigitalDTO dto, final GobernanzaDigital gobernanza) {
        // Mapping básico, relaciones complejas pueden añadirse según se necesite
        return gobernanza;
    }
}

