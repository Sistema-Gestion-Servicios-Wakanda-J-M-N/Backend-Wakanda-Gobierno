package org.example.backendwakandagobierno.service.proyectos;

import org.example.backendwakandagobierno.domain.proyectos.Presupuesto;
import org.example.backendwakandagobierno.model.proyectos.PresupuestoDTO;
import org.example.backendwakandagobierno.repos.PresupuestoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PresupuestoService {

    private final PresupuestoRepository presupuestoRepository;

    public PresupuestoService(final PresupuestoRepository presupuestoRepository) {
        this.presupuestoRepository = presupuestoRepository;
    }

    public List<PresupuestoDTO> findAll() {
        return presupuestoRepository.findAll().stream()
                .map(presupuesto -> mapToDTO(presupuesto, new PresupuestoDTO()))
                .collect(Collectors.toList());
    }

    public PresupuestoDTO get(final Long id) {
        return presupuestoRepository.findById(id)
                .map(presupuesto -> mapToDTO(presupuesto, new PresupuestoDTO()))
                .orElseThrow(() -> new RuntimeException("Presupuesto no encontrado con ID: " + id));
    }

    public Long create(final PresupuestoDTO dto) {
        final Presupuesto presupuesto = mapToEntity(dto, new Presupuesto());
        return presupuestoRepository.save(presupuesto).getId();
    }

    private PresupuestoDTO mapToDTO(final Presupuesto presupuesto, final PresupuestoDTO dto) {
        dto.setId(presupuesto.getId());
        dto.setCategoria(presupuesto.getCategoria());
        dto.setMontoAsignado(presupuesto.getMontoAsignado());
        dto.setMontoGastado(presupuesto.getMontoGastado());
        return dto;
    }

    private Presupuesto mapToEntity(final PresupuestoDTO dto, final Presupuesto presupuesto) {
        presupuesto.setCategoria(dto.getCategoria());
        presupuesto.setMontoAsignado(dto.getMontoAsignado());
        presupuesto.setMontoGastado(dto.getMontoGastado());
        return presupuesto;
    }
}

