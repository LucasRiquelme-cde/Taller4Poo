//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

import java.io.*;
import java.util.*;

import javax.swing.JTextField;

/**
 * Clase principal de lógica del negocio (Backend).
 * Implementa el patrón Singleton para garantizar una única instancia que gestiona
 * todas las listas de datos del sistema.
 * Se encarga de la lectura de archivos, la autenticación y la persistencia completa
 * (Crear, Modificar, Eliminar) de Usuarios y Estudiantes en los archivos de texto.
 */
public class Sistema {

	private static Sistema instancia;
	
	static FactoryUsuario fu;
	static ArrayList<Usuario> listaUsuarios;
	static ArrayList<Estudiante> listaEstudiantes;
	static ArrayList<Curso> listaCursos;
	static ArrayList<Certificacion> listaCertificaciones;

	/**
	 * Constructor privado del Sistema.
	 * Inicializa las listas y la fábrica de usuarios.
	 */
	private Sistema() {
		this.fu = new FactoryUsuario();
		this.listaUsuarios = new ArrayList<Usuario>();
		this.listaEstudiantes = new ArrayList<Estudiante>();
		this.listaCursos = new ArrayList<Curso>();
		this.listaCertificaciones = new ArrayList<Certificacion>();
	}

	/**
	 * Obtiene la instancia única de la clase Sistema.
	 * Si no existe, la crea; si ya existe, devuelve la misma.
	 * @return La instancia única de Sistema.
	 */
	public static Sistema getInstance() {
		if (instancia == null) {
			instancia = new Sistema();
		}
		return instancia;
	}
	
	/**
	 * Inicia la carga masiva de datos desde los archivos de texto.
	 * Ejecuta los métodos de lectura en orden secuencial para asegurar la integridad de los datos.
	 * @throws FileNotFoundException Si alguno de los archivos requeridos no se encuentra.
	 */
	public void iniciar() throws FileNotFoundException {
		listaUsuarios = leerUsuarios("usuarios.txt");
		listaEstudiantes = leerEstudiantes("estudiantes.txt");
		listaCursos = leerCursos("cursos.txt");
		listaCertificaciones = leerCertificaciones("Certificaciones.txt");
		ingresarRegistros("Registros.txt");
		ingresarNotas("Notas.txt");
		crusarCertificaciones("Asignaturas_certificaciones.txt");
	}

	/**
	 * Guarda un NUEVO estudiante al final del archivo "estudiantes.txt" (Modo Append).
	 * Agrega un salto de línea antes de escribir para evitar problemas de formato.
	 * @param e El objeto Estudiante a guardar.
	 */
	public void guardarEstudianteEnArchivo(Estudiante e) {
		try {
			FileWriter fw = new FileWriter("estudiantes.txt", true); 
			PrintWriter pw = new PrintWriter(fw);
			
			String linea = e.getRut() + ";" + 
						   e.getNombre() + ";" + 
						   e.getCarrera() + ";" + 
						   e.getSemestre() + ";" + 
						   e.getCorreo() + ";" + 
						   e.getContraseña();
			
			pw.append("\n" + linea); 
			pw.close();
		} catch (IOException ex) {
			System.out.println("Error guardando en archivo: " + ex.getMessage());
		}
	}

	/**
	 * Guarda un NUEVO usuario (Staff) al final del archivo "usuarios.txt" (Modo Append).
	 * Agrega un salto de línea antes de escribir.
	 * @param u El objeto Usuario a guardar.
	 */
	public void guardarUsuarioEnArchivo(Usuario u) {
		try {
			FileWriter fw = new FileWriter("usuarios.txt", true);
			PrintWriter pw = new PrintWriter(fw);
			
			String linea = u.getNombre() + ";" + u.getContraseña() + ";" + u.getRol();
			
			if (u.getRol().equalsIgnoreCase("Coordinador")) {
				linea += ";" + u.getInfo();
			}
			
			pw.append("\n" + linea);
			pw.close();
		} catch (IOException ex) {
			System.out.println("Error guardando en archivo: " + ex.getMessage());
		}
	}

	/**
	 * REESCRIBE completamente el archivo "estudiantes.txt" con la lista actual de memoria.
	 * Se utiliza después de operaciones de Modificación o Eliminación para sincronizar
	 * los cambios realizados en tiempo de ejecución con el archivo físico.
	 */
	public void actualizarArchivoEstudiantes() {
		try {
			FileWriter fw = new FileWriter("estudiantes.txt", false); // false = Sobreescribir
			PrintWriter pw = new PrintWriter(fw);
			
			for (Estudiante e : listaEstudiantes) {
				String linea = e.getRut() + ";" + 
							   e.getNombre() + ";" + 
							   e.getCarrera() + ";" + 
							   e.getSemestre() + ";" + 
							   e.getCorreo() + ";" + 
							   e.getContraseña();
				pw.println(linea);
			}
			pw.close();
		} catch (IOException ex) {
			System.out.println("Error actualizando archivo estudiantes: " + ex.getMessage());
		}
	}

	/**
	 * REESCRIBE completamente el archivo "usuarios.txt" con la lista actual de memoria.
	 * Se utiliza después de operaciones de Modificación o Eliminación de usuarios del Staff.
	 */
	public void actualizarArchivoUsuarios() {
		try {
			FileWriter fw = new FileWriter("usuarios.txt", false); // false = Sobreescribir
			PrintWriter pw = new PrintWriter(fw);
			
			for (Usuario u : listaUsuarios) {
				String linea = u.getNombre() + ";" + u.getContraseña() + ";" + u.getRol();
				if (u.getRol().equalsIgnoreCase("Coordinador")) {
					linea += ";" + u.getInfo();
				}
				pw.println(linea);
			}
			pw.close();
		} catch (IOException ex) {
			System.out.println("Error actualizando archivo usuarios: " + ex.getMessage());
		}
	}

	/**
	 * Vincula los cursos con las certificaciones leyendo el archivo de relación.
	 * @param string Nombre del archivo.
	 * @throws FileNotFoundException Si el archivo no existe.
	 */
	private void crusarCertificaciones(String string) throws FileNotFoundException {
		Scanner s = new Scanner(new File(string));
		while (s.hasNextLine()) {
			String linea = s.nextLine();
			if (linea.trim().isEmpty()) continue;
			String[] parte = linea.split(";");
			if (parte.length >= 2) {
				String idCertificacion = parte[0];
				String nRCCurso = parte[1];
				for (Certificacion c : listaCertificaciones) {
					if (c.getId().equals(idCertificacion)) {
						for (Curso cu : listaCursos) {
							if (cu.getnRc().equals(nRCCurso)) {
								c.agregarCurso(cu);
							}
						}
					}
				}
			}
		}
		s.close();
	}

	/**
	 * Carga el historial de notas de los estudiantes desde el archivo.
	 * @param string Nombre del archivo.
	 * @throws FileNotFoundException Si el archivo no existe.
	 */
	private void ingresarNotas(String string) throws FileNotFoundException {
		Scanner s = new Scanner(new File(string));
		while (s.hasNextLine()) {
			String linea = s.nextLine();
			if (linea.trim().isEmpty()) continue;
			String[] parte = linea.split(";");
			for (Estudiante e : listaEstudiantes) {
				String rut = parte[0];
				if (e.getRut().equals(rut)) {
					String codigoAsignatura = parte[1];
					double calificacion = Double.valueOf(parte[2]);
					String estado = parte[3];
					String semestre = parte[4];
					Nota n = new Nota(rut, codigoAsignatura, estado, semestre, calificacion);
					e.agregarNota(n);
				}
			}
		}
		s.close();
	}

	/**
	 * Carga los registros de inscripción a certificaciones.
	 * @param string Nombre del archivo.
	 * @throws FileNotFoundException Si el archivo no existe.
	 */
	private void ingresarRegistros(String string) throws FileNotFoundException {
		Scanner s = new Scanner(new File(string));
		while (s.hasNextLine()) {
			String linea = s.nextLine();
			if (linea.trim().isEmpty()) continue;
			String[] parte = linea.split(";");
			for (Estudiante e : listaEstudiantes) {
				String rut = parte[0];
				if (e.getRut().equals(rut)) {
					String idCertificacion = parte[1];
					String fecha = parte[2];
					String estado = parte[3];
					int progreso = Integer.valueOf(parte[4]);
					Registro r = new Registro(rut, idCertificacion, fecha, estado, progreso);
					e.agregarRegistro(r);
				}
			}
		}
		s.close();
	}

	/**
	 * Lee el archivo de definición de certificaciones.
	 * @param string Nombre del archivo.
	 * @return Lista de certificaciones.
	 * @throws FileNotFoundException Si el archivo no existe.
	 */
	private ArrayList<Certificacion> leerCertificaciones(String string) throws FileNotFoundException {
		Scanner s = new Scanner(new File(string));
		ArrayList<Certificacion> lista = new ArrayList<Certificacion>();
		while (s.hasNextLine()) {
			String linea = s.nextLine();
			if (linea.trim().isEmpty()) continue;
			String[] parte = linea.split(";");
			String id = parte[0];
			String nombre = parte[1];
			String descripcion = parte[2];
			String requisitos = parte[3];
			String validez = parte[4];
			Certificacion c = new Certificacion(id, nombre, descripcion, requisitos, validez);
			lista.add(c);
		}
		s.close();
		return lista;
	}

	/**
	 * Lee el archivo de definición de cursos.
	 * @param string Nombre del archivo.
	 * @return Lista de cursos.
	 * @throws FileNotFoundException Si el archivo no existe.
	 */
	private ArrayList<Curso> leerCursos(String string) throws FileNotFoundException {
		Scanner s = new Scanner(new File(string));
		ArrayList<Curso> lista = new ArrayList<Curso>();
		while (s.hasNextLine()) {
			String linea = s.nextLine();
			if (linea.trim().isEmpty()) continue;
			String[] parte = linea.split(";");
			String nRC = parte[0];
			String nombre = parte[1];
			int semestre = Integer.valueOf(parte[2]);
			int creditos = Integer.valueOf(parte[3]);
			String area = parte[4];
			String prerrequisitos = "";
			if (parte.length == 6) {
				prerrequisitos = parte[5];
			}
			Curso c = new Curso(nRC, nombre, area, prerrequisitos, semestre, creditos);
			lista.add(c);
		}
		s.close();
		return lista;
	}

	/**
	 * Lee el archivo de estudiantes.
	 * @param string Nombre del archivo.
	 * @return Lista de estudiantes.
	 * @throws FileNotFoundException Si el archivo no existe.
	 */
	private ArrayList<Estudiante> leerEstudiantes(String string) throws FileNotFoundException {
		Scanner s = new Scanner(new File(string));
		ArrayList<Estudiante> lista = new ArrayList<Estudiante>();
		while (s.hasNextLine()) {
			String linea = s.nextLine();
			if (linea.trim().isEmpty()) continue;
			String[] parte = linea.split(";");
			String rut = parte[0];
			String nombre = parte[1];
			String carrera = parte[2];
			String semestre = parte[3];
			String correo = parte[4];
			String contraseña = parte[5];
			Estudiante e = new Estudiante(rut, nombre, carrera, semestre, correo, contraseña);
			lista.add(e);
		}
		s.close();
		return lista;
	}

	/**
	 * Lee el archivo de usuarios del staff.
	 * @param string Nombre del archivo.
	 * @return Lista de usuarios.
	 * @throws FileNotFoundException Si el archivo no existe.
	 */
	private ArrayList<Usuario> leerUsuarios(String string) throws FileNotFoundException {
		Scanner s = new Scanner(new File(string));
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		while (s.hasNextLine()) {
			String linea = s.nextLine();
			if (linea.trim().isEmpty()) continue;
			String[] parte = linea.split(";");
			String nombre = parte[0];
			String contraseña = parte[1];
			String rol = parte[2];
			String info = "";
			if (rol.equals("Coordinador") && parte.length > 3) {
				info = parte[3];
			}
			Usuario u = fu.getUsuario(nombre, contraseña, rol, info);
			lista.add(u);
		}
		s.close();
		return lista;
	}

	/**
	 * Autentica al usuario en el sistema.
	 * @param usuario Nombre de usuario o RUT.
	 * @param contraseña Contraseña ingresada.
	 * @return Código del menú correspondiente ("A", "C", "E") o cadena vacía si falla.
	 */
	public String getMenu(String usuario, String contraseña) {
		String op = "";
		
		for (Usuario u : listaUsuarios) {
			if (usuario.equals(u.getNombre()) && contraseña.equals(u.getContraseña())) {
				if (u.getRol().equals("Admin")) {
					op = "A";
				} else {
					op = "C";					
				}
			}
		}

		for (Estudiante e : listaEstudiantes) {
			if (usuario.equals(e.getRut()) && contraseña.equals(e.getContraseña())) {
				op = "E";
			}
		}
		return op;
	}

	/**
	 * Obtiene la lista completa de estudiantes.
	 * @return ArrayList de Estudiante.
	 */
	public ArrayList<Estudiante> getlistaEstudiantes() {
		return listaEstudiantes;
	}

	/**
	 * Obtiene la lista completa de cursos.
	 * @return ArrayList de Curso.
	 */
	public ArrayList<Curso> getListaCursos() {
		return listaCursos;
	}

	/**
	 * Obtiene la lista completa de certificaciones.
	 * @return ArrayList de Certificacion.
	 */
	public ArrayList<Certificacion> getListaCertificaciones() {
		return listaCertificaciones;
	}
}