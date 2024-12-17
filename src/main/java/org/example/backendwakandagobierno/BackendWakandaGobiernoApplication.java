package org.example.backendwakandagobierno;

import org.example.backendwakandagobierno.model.gobernanza.GobernanzaDigitalDTO;
import org.example.backendwakandagobierno.model.proyectos.ProyectoLocalDTO;
import org.example.backendwakandagobierno.model.sugerencias.GestorSugerenciasDTO;
import org.example.backendwakandagobierno.model.sugerencias.SugerenciaDTO;
import org.example.backendwakandagobierno.model.tramites.TramiteDTO;
import org.example.backendwakandagobierno.model.usuarios.UsuarioDTO;
import org.example.backendwakandagobierno.service.gobernanza.GobernanzaDigitalService;
import org.example.backendwakandagobierno.service.proyectos.ProyectoLocalService;
import org.example.backendwakandagobierno.service.sugerencias.GestorSugerenciasService;
import org.example.backendwakandagobierno.service.tramites.TramiteService;
import org.example.backendwakandagobierno.service.usuarios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

@EnableScheduling
@SpringBootApplication
@EnableDiscoveryClient
public class BackendWakandaGobiernoApplication implements CommandLineRunner {

	@Autowired
	private GobernanzaDigitalService gobernanzaService;

	@Autowired
	private ProyectoLocalService proyectoService;

	@Autowired
	private GestorSugerenciasService gestorService;

	@Autowired
	private TramiteService tramiteService;

	@Autowired
	private UsuarioService usuarioService;

	public static void main(String[] args) {
		SpringApplication.run(BackendWakandaGobiernoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println(">>> Iniciando creación de datos iniciales... <<<");

		try {
			// Crear Gobernanza Digital
			Long gobernanzaId = crearGobernanzaDigital();

			// Crear Proyectos Locales y asociarlos a GobernanzaDigital
			crearProyectoLocal("Proyecto Vibranium", "Investigación sobre vibranium", gobernanzaId);
			crearProyectoLocal("Proyecto Wakanda", "Desarrollo de infraestructura", gobernanzaId);

			// Crear Gestor de Sugerencias
			crearGestorSugerencias();

			System.out.println(">>> Datos iniciales creados exitosamente <<<");
		} catch (Exception e) {
			System.err.println("Error durante la creación de datos iniciales: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private Long crearGobernanzaDigital() {
		GobernanzaDigitalDTO gobernanza = new GobernanzaDigitalDTO();
		return gobernanzaService.create(gobernanza);
	}

	private Long crearProyectoLocal(String nombre, String descripcion, Long gobernanzaId) {
		ProyectoLocalDTO proyecto = new ProyectoLocalDTO();
		proyecto.setNombre(nombre);
		proyecto.setDescripcion(descripcion);
		proyecto.setFechaInicio(new Date());
		proyecto.setFechaFin(new Date());
		proyecto.setEstado("PROPUESTO");
		return proyectoService.create(proyecto, gobernanzaId);
	}

	private Long crearGestorSugerencias() {
		GestorSugerenciasDTO gestor = new GestorSugerenciasDTO();
		gestor.setEstado("ACTIVO");
		gestor.setFechaCreacion(new Date());
		return gestorService.create(gestor);
	}
}
