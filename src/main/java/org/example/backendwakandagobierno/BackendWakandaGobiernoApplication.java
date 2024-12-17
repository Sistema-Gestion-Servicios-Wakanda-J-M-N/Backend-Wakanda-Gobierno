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
			// Crear Usuarios
			Long usuario1Id = crearUsuario("Pepe", "Garcia", "pepe.garcia@example.com");
			Long usuario2Id = crearUsuario("Pepa", "Gutierrez", "pepa.gutierrez@example.com");

			// Crear Gobernanza Digital
			Long gobernanzaId = crearGobernanzaDigital();

			// Crear Proyectos Locales y asociarlos a GobernanzaDigital
			Long proyecto1Id = crearProyectoLocal("Proyecto Vibranium", "Investigación sobre vibranium", gobernanzaId);
			Long proyecto2Id = crearProyectoLocal("Proyecto Wakanda", "Desarrollo de infraestructura", gobernanzaId);

			// Crear Gestor de Sugerencias
			Long gestorId = crearGestorSugerencias();

			// Generar Sugerencias Periódicas
			generarSugerenciasParaProyectos(gestorId, Arrays.asList(proyecto1Id, proyecto2Id), usuario1Id);

			// Iniciar Trámites con un usuario
			Long tramite1Id = iniciarTramite(usuario1Id, "REGISTRO");
			Long tramite2Id = iniciarTramite(usuario2Id, "PERMISO");

			// Consultar estado de los trámites
			consultarEstadoTramite(tramite1Id);
			consultarEstadoTramite(tramite2Id);

			System.out.println(">>> Datos iniciales creados exitosamente <<<");
		} catch (Exception e) {
			System.err.println("Error durante la creación de datos iniciales: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private Long crearUsuario(String nombre, String apellido, String email) {
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setNombre(nombre);
		usuario.setApellidos(apellido);
		usuario.setEmail(email);
		usuario.setRoles(List.of("Usuario"));
		return usuarioService.create(usuario);
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

	private void generarSugerenciasParaProyectos(Long gestorId, List<Long> proyectosIds, Long usuarioId) {
		proyectosIds.forEach(proyectoId -> {
			SugerenciaDTO sugerencia = new SugerenciaDTO();
			sugerencia.setDescripcion("Sugerencia generada automáticamente para proyecto " + proyectoId);
			sugerencia.setFechaEnvio(new Date());
			sugerencia.setEstado("ENVIADA");
			sugerencia.setUsuarioId(usuarioId);
			gestorService.visualizarSugerencias(gestorId);
			System.out.println("Sugerencia creada para Proyecto ID: " + proyectoId);
		});
	}

	private Long iniciarTramite(Long usuarioId, String tipo) {
		TramiteDTO tramite = new TramiteDTO();
		tramite.setTipo(tipo);
		tramite.setFechaSolicitud(new Date());
		Long tramiteId = tramiteService.iniciarTramite(usuarioId, tramite);
		System.out.println("Trámite iniciado con ID: " + tramiteId + " para Usuario ID: " + usuarioId);
		return tramiteId;
	}

	private void consultarEstadoTramite(Long tramiteId) {
		String estadoTramite = tramiteService.consultarEstado(tramiteId);
		System.out.println("Estado del Trámite ID " + tramiteId + ": " + estadoTramite);
	}
}
