package org.example.backendwakandagobierno.controller;

import org.example.backendwakandagobierno.model.tramites.TramiteDTO;
import org.example.backendwakandagobierno.service.tramites.TramiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tramites")
public class TramiteController {

    @Autowired
    private TramiteService tramiteService;

    @PostMapping("/iniciar")
    public Long iniciarTramite(@RequestParam Long usuarioId, @RequestBody TramiteDTO tramiteDTO) {
        return tramiteService.iniciarTramite(usuarioId, tramiteDTO);
    }
}
