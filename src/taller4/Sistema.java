//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTextField;

/**
 * Clase principal de lógica del negocio (Backend).
 * Implementa el patrón de diseño Singleton para garantizar una única instancia
 * que gestiona todas las listas de datos (Usuarios, Estudiantes, Cursos, Certificaciones).
 * Se encarga de la lectura de archivos de texto y la autenticación.
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
		 * Es privado para prevenir la instanciación directa (Patrón Singleton).
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
		 * * @return La instancia única de Sistema.
		 */
		public static Sistema getInstance() {
			if (instancia == null) {
				instancia = new Sistema();
			}
			return instancia;
		}
	
	
	/**
	 * Inicia la carga masiva de datos desde los archivos de texto.
	 * Ejecuta los métodos de lectura en orden secuencial para asegurar la integridad referencial.
	 * * @throws FileNotFoundException Si alguno de los archivos requeridos no se encuentra en la raíz.
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
	 * Vincula los cursos con las certificaciones correspondientes.
	 * Lee el archivo de relación y agrega los objetos Curso a la lista interna de cada Certificación.
	 * * @param string Nombre del archivo a leer ("Asignaturas_certificaciones.txt").
	 * @throws FileNotFoundException Si el archivo no existe.
	 */
	private void crusarCertificaciones(String string) throws FileNotFoundException {
		Scanner s = new Scanner(new File(string));
		while (s.hasNextLine()) {
			String linea = s.nextLine();
			String[] parte = linea.split(";");
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

		s.close();
	}

	/**
	 * Carga las notas de los estudiantes desde un archivo.
	 * Asocia cada calificación al estudiante correspondiente buscándolo por RUT.
	 * * @param string Nombre del archivo ("Notas.txt").
	 * @throws FileNotFoundException Si el archivo no existe.
	 */
	private void ingresarNotas(String string) throws FileNotFoundException {
		Scanner s = new Scanner(new File(string));
		while (s.hasNextLine()) {
			String linea = s.nextLine();
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
	 * Carga los registros de certificaciones (inscripciones) de los estudiantes.
	 * * @param string Nombre del archivo ("Registros.txt").
	 * @throws FileNotFoundException Si el archivo no existe.
	 */
	private void ingresarRegistros(String string) throws FileNotFoundException {
		Scanner s = new Scanner(new File(string));
		while (s.hasNextLine()) {
			String linea = s.nextLine();
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
	 * Lee el archivo de Certificaciones y crea los objetos correspondientes.
	 * * @param string Nombre del archivo.
	 * @return ArrayList con las certificaciones cargadas.
	 * @throws FileNotFoundException Si el archivo no existe.
	 */
	private ArrayList<Certificacion> leerCertificaciones(String string) throws FileNotFoundException {
		Scanner s = new Scanner(new File(string));
		ArrayList<Certificacion> lista = new ArrayList<Certificacion>();
		while (s.hasNextLine()) {
			String linea = s.nextLine();
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
	 * Lee el archivo de Cursos (asignaturas) y crea los objetos.
	 * Maneja la lógica para prerrequisitos opcionales.
	 * * @param string Nombre del archivo.
	 * @return ArrayList con los cursos cargados.
	 * @throws FileNotFoundException Si el archivo no existe.
	 */
	private ArrayList<Curso> leerCursos(String string) throws FileNotFoundException {
		Scanner s = new Scanner(new File(string));
		ArrayList<Curso> lista = new ArrayList<Curso>();
		while (s.hasNextLine()) {
			String linea = s.nextLine();
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
	 * Lee el archivo de Estudiantes y crea los objetos.
	 * * @param string Nombre del archivo.
	 * @return ArrayList con los estudiantes cargados.
	 * @throws FileNotFoundException Si el archivo no existe.
	 */
	private ArrayList<Estudiante> leerEstudiantes(String string) throws FileNotFoundException {
		Scanner s = new Scanner(new File(string));
		ArrayList<Estudiante> lista = new ArrayList<Estudiante>();
		while (s.hasNextLine()) {
			String linea = s.nextLine();
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
	 * Lee el archivo de Usuarios (Staff) y utiliza el FactoryUsuario para crearlos.
	 * * @param string Nombre del archivo ("usuarios.txt").
	 * @return ArrayList con los usuarios (Administradores y Coordinadores).
	 * @throws FileNotFoundException Si el archivo no existe.
	 */
	private ArrayList<Usuario> leerUsuarios(String string) throws FileNotFoundException {
		Scanner s = new Scanner(new File(string));
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		while (s.hasNextLine()) {
			String linea = s.nextLine();
			String[] parte = linea.split(";");
			String nombre = parte[0];
			String contraseña = parte[1];
			String rol = parte[2];
			String info = "";
			if (rol.equals("Coordinador")) {
				info = parte[3];
			}
			Usuario u = fu.getUsuario(nombre, contraseña, rol, info);
			lista.add(u);
		}
		s.close();
		return lista;

	}

	/**
	 * Realiza la autenticación de un usuario en el sistema.
	 * Busca tanto en la lista de usuarios (staff) como en la de estudiantes.
	 * * @param usuario    Nombre de usuario o RUT.
	 * @param contraseña Contraseña ingresada.
	 * @return Un código indicando el tipo de menú a mostrar:
	 * "A" para Administrador,
	 * "C" para Coordinador,
	 * "E" para Estudiante,
	 * "" (cadena vacía) si las credenciales son incorrectas.
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
	 * Obtiene la lista completa de estudiantes cargados.
	 * @return ArrayList de Estudiantes.
	 */
	public ArrayList<Estudiante> getlistaEstudiantes() {
		return listaEstudiantes;
	}

	/**
	 * Obtiene la lista completa de cursos cargados.
	 * @return ArrayList de Cursos.
	 */
	public ArrayList<Curso> getListaCursos() {
		return listaCursos;
	}

	/**
	 * Obtiene la lista completa de certificaciones disponibles.
	 * @return ArrayList de Certificaciones.
	 */
	public ArrayList<Certificacion> getListaCertificaciones() {
		return listaCertificaciones;
	}

}