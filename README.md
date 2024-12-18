# Backend-Wakanda-Gobierno

Organizacion con todos --> https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N 


1. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Frontend-Wakanda
2. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-API-Central   -> API-Central (Gestiona los microservicios y muestra un tablero de Wakanda).
3. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Eureka-Server   -> Eureka-Server/Grafana/Prometheus
4. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/InitManager   -> Lanza el Eureka-Server/Prometheus/Grafana y ejecuta un script para lanzar los microservicios.
5. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Python-Script-Launcher   -> Script para lanzar los microservicios en orden.
6. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend_Wakanda_Salud
7. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Agua
8. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Transporte-Movilidad
9. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Gobierno
10. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Cultura-Ocio-Turismo
11. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Trafico
12. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Seguridad
13. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Residuos
14. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Conectividad-Redes
15. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Energia-Sostenible-Eficiente
16. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Servicios-Emergencia
17. https://github.com/Sistema-Gestion-Servicios-Wakanda-J-M-N/Backend-Wakanda-Educacion

## Participantes del proyecto

- Jaime López Díaz
- Marcos García Benito
- Nicolas Jimenez
- Juan Manuel

## Servicios
Este microservicio basado en gestión Gubernamental en Wakanda, contiene varios servicios responsables de gestionar diferentes aspectos del sistema. A continuación, se presenta un desglose de los servicios:

### UsuarioService
El `UsuarioService` es responsable de gestionar las cuentas de usuario. Proporciona operaciones CRUD para los usuarios y sus credenciales asociadas.
**Métodos:**
- **`findAll()`**: Recupera una lista de todas las cuentas de usuario.
- **`get(Long id)`**: Recupera una cuenta de usuario específica por su ID.
- **`create(UsuarioDTO dto)`**: Crea una nueva cuenta de usuario junto con sus credenciales.
- **`update(Long id, UsuarioDTO dto)`**: Actualiza una cuenta de usuario existente y sus credenciales.
- **`delete(Long id)`**: Elimina una cuenta de usuario y sus credenciales asociadas.
  
### SugerenciaService
El `SugerenciaService` gestiona las sugerencias asociadas a proyectos y usuarios. Proporciona operaciones CRUD, métodos de asignación y generación automática de sugerencias.

**Métodos:**
- **`findAll()`**: Recupera una lista de todas las sugerencias.
- **`get(Long id)`**: Recupera una sugerencia específica por su ID.
- **`create(SugerenciaDTO dto)`**: Crea una nueva sugerencia.
- **`findAllByProyectoId(Long proyectoId)`**: Recupera todas las sugerencias asociadas a un proyecto específico.
- **`createSugerenciaForProyecto(Long proyectoId, SugerenciaDTO dto)`**: Crea y asigna una sugerencia a un proyecto y su gestor de sugerencias asociado.
- **`generateRandomSugerenciaTask()`**: Genera sugerencias aleatorias de forma periódica para proyectos y usuarios disponibles.
- **`generateRandomSugerencia(Long proyectoId, Long usuarioId)`**: Crea una sugerencia generada aleatoriamente para un proyecto y usuario específicos.

---

### GestorSugerenciasService
El `GestorSugerenciasService` maneja los gestores de sugerencias, incluyendo la creación y recuperación de sugerencias activas.

**Métodos:**
- **`get(Long id)`**: Recupera un gestor de sugerencias específico por su ID.
- **`create(GestorSugerenciasDTO dto)`**: Crea un nuevo gestor de sugerencias con un estado predeterminado y fecha de creación asignada.
- **`visualizarSugerencias(Long gestorId)`**: Recupera las sugerencias asociadas a un gestor con estados "ENVIADA" o "REVISADA".

---

### ProyectoLocalService
El `ProyectoLocalService` gestiona los proyectos locales y su asociación con una gobernanza digital. Proporciona operaciones CRUD y métodos de asignación.

**Métodos:**
- **`findAll()`**: Recupera una lista de todos los proyectos locales.
- **`get(Long id)`**: Recupera un proyecto local específico por su ID.
- **`create(ProyectoLocalDTO proyectoDTO, Long gobernanzaId)`**: Crea un nuevo proyecto local asociado a una gobernanza digital específica.

---

### GobernanzaDigitalService
El `GobernanzaDigitalService` gestiona la gobernanza digital y sus proyectos asociados. Proporciona operaciones CRUD y métodos para listar proyectos relacionados.

**Métodos:**
- **`findAll()`**: Recupera una lista de todas las gobernanzas digitales.
- **`get(Long id)`**: Recupera una gobernanza digital específica por su ID.
- **`create(GobernanzaDigitalDTO dto)`**: Crea una nueva gobernanza digital, incluyendo un gestor de sugerencias activo por defecto.
- **`delete(Long id)`**: Elimina una gobernanza digital específica.
- **`listarProyectos(Long gobernanzaId)`**: Recupera una lista de proyectos locales asociados a una gobernanza digital específica.

---

### TramiteService
El `TramiteService` gestiona los trámites asociados a usuarios. Proporciona operaciones CRUD y métodos únicos para iniciar y consultar trámites.

**Métodos:**
- **`findAll()`**: Recupera una lista de todos los trámites.
- **`get(Long id)`**: Recupera un trámite específico por su ID.
- **`create(TramiteDTO dto)`**: Crea un nuevo trámite.
- **`iniciarTramite(Long usuarioId, TramiteDTO tramiteDTO)`**: Inicia un trámite para un usuario específico y lo marca como "EN PROCESO".
- **`consultarEstado(Long tramiteId)`**: Consulta el estado de un trámite específico.

## Endpoints REST
El proyecto también incluye endpoints REST para interactuar con los servicios. Endpoints:

### Endpoints de Usuario
- **`/usuarios`**: GET (recuperar todos los usuarios), POST (crear un nuevo usuario)
- **`/usuarios/{id}`**: GET (recuperar un usuario por ID), PUT (actualizar un usuario por ID), DELETE (eliminar un usuario por ID)

## Endpoints REST-Controller:
  
### Endpoints de Tramite
- **`tramites/iniciar`**: POST (iniciar un nuevo trámite para un usuario específico)

### Endpoints de Sugerencia
- **`sugerencias/proyecto/{proyectoId}`**: GET (recuperar todas las sugerencias asociadas a un proyecto específico)
- **`sugerencias`**: GET (recuperar todas las sugerencias)
