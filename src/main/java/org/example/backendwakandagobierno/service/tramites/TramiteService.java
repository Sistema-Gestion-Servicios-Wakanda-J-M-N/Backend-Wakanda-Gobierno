package org.example.backendwakandagobierno.service.tramites;

import org.example.backendwakandagobierno.aop.anotaciones.Auditable;
import org.example.backendwakandagobierno.domain.tramites.Tramite;
import org.example.backendwakandagobierno.domain.usuarios.Usuario;
import org.example.backendwakandagobierno.model.tramites.TramiteDTO;
import org.example.backendwakandagobierno.repos.TramiteRepository;
import org.example.backendwakandagobierno.repos.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TramiteService {

    private final TramiteRepository tramiteRepository;
    private final UsuarioRepository usuarioRepository;

    public TramiteService(final TramiteRepository tramiteRepository, final UsuarioRepository usuarioRepository) {
        this.tramiteRepository = tramiteRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public List<TramiteDTO> findAll() {
        return tramiteRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }



    public TramiteDTO get(final Long id) {
        return tramiteRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new RuntimeException("Trámite no encontrado con ID: " + id));
    }



    @Auditable
    public Long create(final TramiteDTO dto) {
        final Tramite tramite = mapToEntity(dto, new Tramite());
        return tramiteRepository.save(tramite).getId();
    }



    @Auditable
    // Nuevo: metodo único para iniciar trámite (más completo)
    public Long iniciarTramite(final Long usuarioId, final TramiteDTO tramiteDTO) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + usuarioId));

        Tramite tramite = new Tramite();
        tramite.setTipo(tramiteDTO.getTipo());
        tramite.setEstado("EN PROCESO");
        tramite.setFechaSolicitud(new Date());
        tramite.setUsuario(usuario);

        return tramiteRepository.save(tramite).getId();
    }



    @Auditable
    // Nuevo: metodo único para consultar el estado del trámite
    public String consultarEstadoDelTramite(final Long tramiteId) {
        return tramiteRepository.findById(tramiteId)
                .map(Tramite::getEstado)
                .orElseThrow(() -> new RuntimeException("Trámite no encontrado con ID: " + tramiteId));
    }



    private TramiteDTO mapToDTO(final Tramite tramite) {
        TramiteDTO dto = new TramiteDTO();
        dto.setId(tramite.getId());
        dto.setTipo(tramite.getTipo());
        dto.setEstado(tramite.getEstado());
        dto.setFechaSolicitud(tramite.getFechaSolicitud());
        dto.setFechaResolucion(tramite.getFechaResolucion());
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

