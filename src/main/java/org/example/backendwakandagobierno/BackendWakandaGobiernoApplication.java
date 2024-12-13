package org.example.backendwakandagobierno;

import org.example.backendwakandagobierno.model.gobernanza.GobernanzaDigitalDTO;
import org.example.backendwakandagobierno.model.proyectos.ProyectoLocalDTO;
import org.example.backendwakandagobierno.model.sugerencias.GestorSugerenciasDTO;
import org.example.backendwakandagobierno.model.tramites.TramiteDTO;
import org.example.backendwakandagobierno.model.usuarios.CredencialesDTO;
import org.example.backendwakandagobierno.model.usuarios.UsuarioDTO;
import org.example.backendwakandagobierno.service.gobernanza.GobernanzaDigitalService;
import org.example.backendwakandagobierno.service.proyectos.ProyectoLocalService;
import org.example.backendwakandagobierno.service.sugerencias.GestorSugerenciasService;
import org.example.backendwakandagobierno.service.tramites.TramiteService;
import org.example.backendwakandagobierno.service.usuarios.CredencialesService;
import org.example.backendwakandagobierno.service.usuarios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
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

	@Autowired
	private CredencialesService credencialesService;

	public static void main(String[] args) {
		SpringApplication.run(BackendWakandaGobiernoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println(">>> Iniciando pruebas de servicios... <<<");

		try {
			UsuarioDTO nuevoUsuario = new UsuarioDTO();
			nuevoUsuario.setNombre("Juan");
			nuevoUsuario.setApellidos("Perez");
			nuevoUsuario.setEmail("juan.perez@example.com");
			nuevoUsuario.setRoles(List.of("Usuario"));

			// 1. CREATE usuario
			Long usuarioId = usuarioService.create(nuevoUsuario);
			System.out.println("Usuario creado con ID: " + usuarioId);

			// GET usuario
			UsuarioDTO usuarioObtenido = usuarioService.get(usuarioId);
			System.out.println("Usuario obtenido: " + usuarioObtenido);

			// UPDATE usuario
			usuarioObtenido.setNombre("Juan Actualizado");
			usuarioObtenido.setApellidos("Perez Actualizado");
			usuarioService.update(usuarioId, usuarioObtenido);
			UsuarioDTO usuarioActualizado = usuarioService.get(usuarioId);
			System.out.println("Usuario luego de update: " + usuarioActualizado);

			// 2. FIND ALL usuarios
			List<UsuarioDTO> listaUsuarios = usuarioService.findAll();
			System.out.println("Lista de usuarios: " + listaUsuarios);

			// 3. Crear una Gobernanza Digital
			GobernanzaDigitalDTO gobernanzaDTO = new GobernanzaDigitalDTO();
			gobernanzaDTO.setProyectosLocalesIds(List.of()); // Inicialmente sin proyectos
			Long gobernanzaId = gobernanzaService.create(gobernanzaDTO);
			System.out.println("Gobernanza creada con ID: " + gobernanzaId);

			// 4. Crear un Proyecto Local y asociarlo a GobernanzaDigital
			ProyectoLocalDTO proyectoDTO = new ProyectoLocalDTO();
			proyectoDTO.setNombre("Proyecto Vibranium");
			proyectoDTO.setDescripcion("Investigación del Vibranium en Wakanda");
			proyectoDTO.setFechaInicio(new Date());
			proyectoDTO.setFechaFin(new Date());
			proyectoDTO.setEstado("PROPUESTO");

			Long proyectoId = proyectoService.create(proyectoDTO, gobernanzaId);
			System.out.println("Proyecto Local creado con ID: " + proyectoId);

			// 5. Crear un Gestor de Sugerencias
			GestorSugerenciasDTO gestorDTO = new GestorSugerenciasDTO();
			gestorDTO.setEstado("ACTIVO");
			Long gestorId = gestorService.create(gestorDTO);
			System.out.println("Gestor de Sugerencias creado con ID: " + gestorId);

			// 6. Iniciar un Trámite con el ID del Usuario
			TramiteDTO tramiteDTO = new TramiteDTO();
			tramiteDTO.setTipo("REGISTRO");
			tramiteDTO.setFechaSolicitud(new Date());
			Long tramiteId = tramiteService.iniciarTramite(usuarioId, tramiteDTO);
			System.out.println("Trámite iniciado con ID: " + tramiteId + " para el Usuario ID: " + usuarioId);

			// 7. Consultar estado del trámite
			String estadoTramite = tramiteService.consultarEstado(tramiteId);
			System.out.println("Estado del Trámite ID " + tramiteId + ": " + estadoTramite);

			System.out.println(">>> Pruebas completadas exitosamente <<<");
		} catch (Exception e) {
			System.err.println("Error durante las pruebas: " + e.getMessage());
		}
	}
}
