package org.example.backendwakandagobierno.service.sugerencias;

import org.example.backendwakandagobierno.domain.sugerencias.Sugerencia;
import org.example.backendwakandagobierno.model.sugerencias.SugerenciaDTO;
import org.example.backendwakandagobierno.repos.SugerenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SugerenciaService {

    private final SugerenciaRepository sugerenciaRepository;

    public SugerenciaService(final SugerenciaRepository sugerenciaRepository) {
        this.sugerenciaRepository = sugerenciaRepository;
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

