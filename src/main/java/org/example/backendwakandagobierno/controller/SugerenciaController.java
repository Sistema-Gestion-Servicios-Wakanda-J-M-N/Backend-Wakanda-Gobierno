package org.example.backendwakandagobierno.controller;


import org.example.backendwakandagobierno.model.sugerencias.SugerenciaDTO;
import org.example.backendwakandagobierno.service.sugerencias.SugerenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sugerencias")
public class SugerenciaController {

    @Autowired
    private SugerenciaService sugerenciaService;

    @GetMapping("/proyecto/{proyectoId}")
    public List<SugerenciaDTO> getSugerenciasByProyecto(@PathVariable Long proyectoId) {
        return sugerenciaService.findAllByProyectoId(proyectoId);
    }

    @GetMapping
    public List<SugerenciaDTO> getAllSugerencias() {
        return sugerenciaService.findAll();
    }
}
