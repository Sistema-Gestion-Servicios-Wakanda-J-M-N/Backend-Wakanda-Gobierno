package org.example.backendwakandagobierno.service.tramites;

import org.example.backendwakandagobierno.domain.tramites.Tramite;
import org.example.backendwakandagobierno.model.tramites.TramiteDTO;
import org.example.backendwakandagobierno.repos.TramiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TramiteService {

    private final TramiteRepository tramiteRepository;

    public TramiteService(final TramiteRepository tramiteRepository) {
        this.tramiteRepository = tramiteRepository;
    }

    public List<TramiteDTO> findAll() {
        return tramiteRepository.findAll().stream()
                .map(tramite -> mapToDTO(tramite, new TramiteDTO()))
                .collect(Collectors.toList());
    }

    public TramiteDTO get(final Long id) {
        return tramiteRepository.findById(id)
                .map(tramite -> mapToDTO(tramite, new TramiteDTO()))
                .orElseThrow(() -> new RuntimeException("Tramite no encontrado con ID: " + id));
    }

    public Long create(final TramiteDTO dto) {
        final Tramite tramite = mapToEntity(dto, new Tramite());
        return tramiteRepository.save(tramite).getId();
    }

    private TramiteDTO mapToDTO(final Tramite tramite, final TramiteDTO dto) {
        dto.setId(tramite.getId());
        dto.setTipo(tramite.getTipo());
        dto.setEstado(tramite.getEstado());
        dto.setFechaSolicitud(tramite.getFechaSolicitud());
        dto.setFechaResolucion(tramite.getFechaResolucion());
        dto.setUsuarioId(tramite.getUsuario() != null ? tramite.getUsuario().getId() : null);
        return dto;
    }

    private Tramite mapToEntity(final TramiteDTO dto, final Tramite tramite) {
        tramite.setTipo(dto.getTipo());
        tramite.setEstado(dto.getEstado());
        tramite.setFechaSolicitud(dto.getFechaSolicitud());
        tramite.setFechaResolucion(dto.getFechaResolucion());
        return tramite;
    }
}

