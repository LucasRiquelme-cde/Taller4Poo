// Matías Collao / 22.060.152-8 / ICCI
// Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

import java.util.ArrayList;
import taller4.escritura.GestorArchivos; 
import taller4.modelo.*; 

/**
 * Clase principal que actúa como el controlador central del sistema (Patrón Singleton).
 * <p>
 * Su responsabilidad es mantener el estado de la aplicación en memoria (las listas de datos)
 * y servir como puente entre la Capa de Presentación (Vistas) y la Capa de Acceso a Datos (DAO).
 * </p>
 */
public class Sistema {

    private static Sistema instancia;
    
    /** Objeto encargado de la lectura y escritura en archivos físicos (Capa DAO). */
    private GestorArchivos gestorArchivos; 
    
    /** Lista en memoria que almacena todos los usuarios del staff (Administradores y Coordinadores). */
    public static ArrayList<Usuario> listaUsuarios;
    
    /** Lista en memoria que almacena todos los estudiantes registrados. */
    public static ArrayList<Estudiante> listaEstudiantes;
    
    /** Lista en memoria que almacena el catálogo de cursos disponibles. */
    public static ArrayList<Curso> listaCursos;
    
    /** Lista en memoria que almacena las certificaciones disponibles. */
    public static ArrayList<Certificacion> listaCertificaciones;
    
    /** Fábrica para la creación de objetos de tipo Usuario (Patrón Factory Method). */
    public static FactoryUsuario fu; 

    /**
     * Constructor privado para implementar el patrón Singleton.
     * Inicializa las listas vacías y las instancias auxiliares.
     */
    private Sistema() {
        this.gestorArchivos = new GestorArchivos();
        this.fu = new FactoryUsuario();
        
        listaUsuarios = new ArrayList<>();
        listaEstudiantes = new ArrayList<>();
        listaCursos = new ArrayList<>();
        listaCertificaciones = new ArrayList<>();
    }

    /**
     * Obtiene la instancia única de la clase Sistema.
     * Si la instancia no existe, la crea; de lo contrario, devuelve la existente.
     * * @return La instancia única de Sistema.
     */
    public static Sistema getInstance() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }
    
    /**
     * Inicia el sistema cargando todos los datos desde la capa de persistencia (Archivos).
     * <p>
     * El orden de carga es secuencial: primero se cargan las entidades maestras (Usuarios, 
     * Estudiantes, Cursos, Certificaciones) y luego se cargan las relaciones dependientes 
     * (Notas, Registros y Cruce de Cursos-Certificaciones).
     * </p>
     */
    public void iniciar() {
        System.out.println("Iniciando sistema... cargando datos.");
        listaUsuarios = gestorArchivos.leerUsuarios("usuarios.txt", fu);
        listaEstudiantes = gestorArchivos.leerEstudiantes("estudiantes.txt");
        listaCursos = gestorArchivos.leerCursos("cursos.txt");
        listaCertificaciones = gestorArchivos.leerCertificaciones("Certificaciones.txt");
        gestorArchivos.cargarRegistros("Registros.txt", listaEstudiantes);
        gestorArchivos.cargarNotas("Notas.txt", listaEstudiantes);
        gestorArchivos.cruzarCertificaciones("Asignaturas_certificaciones.txt", listaCertificaciones, listaCursos);
        
        System.out.println("Datos cargados correctamente.");
    }
    /**
     * Guarda un nuevo estudiante en el archivo físico delegando la tarea al DAO.
     * * @param e El objeto Estudiante a persistir.
     */
    public void guardarEstudianteEnArchivo(Estudiante e) {
        gestorArchivos.guardarEstudiante(e, "estudiantes.txt");
    }

    /**
     * Guarda un nuevo usuario del staff (Admin/Coordinador) en el archivo físico.
     * * @param u El objeto Usuario a persistir.
     */
    public void guardarUsuarioEnArchivo(Usuario u) {
        gestorArchivos.guardarUsuario(u, "usuarios.txt");
    }

    /**
     * Actualiza completamente el archivo de estudiantes con el estado actual de la lista en memoria.
     * Se utiliza tras operaciones de modificación o eliminación.
     */
    public void actualizarArchivoEstudiantes() {
        gestorArchivos.actualizarArchivoEstudiantes(listaEstudiantes, "estudiantes.txt");
    }

    /**
     * Actualiza completamente el archivo de usuarios con el estado actual de la lista en memoria.
     * Se utiliza tras operaciones de modificación o eliminación.
     */
    public void actualizarArchivoUsuarios() {
        gestorArchivos.actualizarArchivoUsuarios(listaUsuarios, "usuarios.txt");
    }
    /**
     * Valida las credenciales de un usuario e identifica su rol para determinar el menú a mostrar.
     * Busca tanto en la lista de Staff como en la de Estudiantes.
     * * @param usuario    Nombre de usuario o RUT.
     * @param contraseña Contraseña ingresada.
     * @return Un código de String que representa el tipo de menú:
     * <ul>
     * <li>"A": Administrador</li>
     * <li>"C": Coordinador</li>
     * <li>"E": Estudiante</li>
     * <li>"": Credenciales inválidas</li>
     * </ul>
     */
    public String getMenu(String usuario, String contraseña) {
        for (Usuario u : listaUsuarios) {
            if (u.getNombre().equals(usuario) && u.getContraseña().equals(contraseña)) {
                return u.getRol().equals("Admin") ? "A" : "C";
            }
        }
        for (Estudiante e : listaEstudiantes) {
            if (e.getRut().equals(usuario) && e.getContraseña().equals(contraseña)) { 
                 return "E";
            }
        }
        
        return "";
    }

    /**
     * Obtiene la lista completa de estudiantes cargados en el sistema.
     * @return ArrayList de objetos Estudiante.
     */
    public ArrayList<Estudiante> getlistaEstudiantes() { return listaEstudiantes; }

    /**
     * Obtiene la lista completa de cursos cargados en el sistema.
     * @return ArrayList de objetos Curso.
     */
    public ArrayList<Curso> getListaCursos() { return listaCursos; }

    /**
     * Obtiene la lista completa de certificaciones disponibles.
     * @return ArrayList de objetos Certificacion.
     */
    public ArrayList<Certificacion> getListaCertificaciones() { return listaCertificaciones; }

    /**
     * Obtiene la lista completa de usuarios del staff.
     * @return ArrayList de objetos Usuario.
     */
    public ArrayList<Usuario> getListaUsuarios() { return listaUsuarios; }
}