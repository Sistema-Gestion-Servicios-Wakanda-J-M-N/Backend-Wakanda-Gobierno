package org.example.backendwakandagobierno.service.sugerencias;

import org.example.backendwakandagobierno.domain.sugerencias.GestorSugerencias;
import org.example.backendwakandagobierno.domain.sugerencias.Sugerencia;
import org.example.backendwakandagobierno.model.sugerencias.GestorSugerenciasDTO;
import org.example.backendwakandagobierno.model.sugerencias.SugerenciaDTO;
import org.example.backendwakandagobierno.repos.GestorSugerenciasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestorSugerenciasService {

    private final GestorSugerenciasRepository gestorSugerenciasRepository;

    public GestorSugerenciasService(final GestorSugerenciasRepository gestorSugerenciasRepository) {
        this.gestorSugerenciasRepository = gestorSugerenciasRepository;
    }

    public GestorSugerenciasDTO get(final Long id) {
        return gestorSugerenciasRepository.findById(id)
                .map(this::mapGestorToDTO)
                .orElseThrow(() -> new RuntimeException("Gestor de sugerencias no encontrado con ID: " + id));
    }



    public Long create(final GestorSugerenciasDTO dto) {
        final GestorSugerencias gestor = mapToEntity(dto, new GestorSugerencias());
        return gestorSugerenciasRepository.save(gestor).getId();
    }


    // Nuevo: metodo para obtener sugerencias activas
    public List<SugerenciaDTO> obtenerSugerenciasActivas(final Long gestorId) {
        GestorSugerencias gestor = gestorSugerenciasRepository.findById(gestorId)
                .orElseThrow(() -> new RuntimeException("Gestor de sugerencias no encontrado con ID: " + gestorId));
        return gestor.getSugerencias().stream()
                .filter(sugerencia -> sugerencia.getEstado().equals("ENVIADA") || sugerencia.getEstado().equals("REVISADA"))
                .map(this::mapSugerenciaToDTO) // Cambiamos aquí al metodo correcto para Sugerencia
                .collect(Collectors.toList());
    }



    // metodo para mapear un GestorSugerencias a su DTO
    private GestorSugerenciasDTO mapGestorToDTO(final GestorSugerencias gestor) {
        GestorSugerenciasDTO dto = new GestorSugerenciasDTO();
        dto.setId(gestor.getId());
        dto.setEstado(gestor.getEstado());
        return dto;
    }



    // metodo para mapear una Sugerencia a su DTO
    private SugerenciaDTO mapSugerenciaToDTO(final Sugerencia sugerencia) {
        SugerenciaDTO dto = new SugerenciaDTO();
        dto.setId(sugerencia.getId());
        dto.setDescripcion(sugerencia.getDescripcion());
        dto.setFechaEnvio(sugerencia.getFechaEnvio());
        dto.setEstado(sugerencia.getEstado());
        dto.setUsuarioId(sugerencia.getUsuario() != null ? sugerencia.getUsuario().getId() : null);
        return dto;
    }



    private GestorSugerencias mapToEntity(final GestorSugerenciasDTO dto, final GestorSugerencias gestor) {
        gestor.setEstado(dto.getEstado());
        return gestor;
    }
}


