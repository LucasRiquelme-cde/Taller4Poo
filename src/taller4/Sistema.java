package taller4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTextField;

public class Sistema {

	
		private static Sistema instancia;
		
		static FactoryUsuario fu;
		static ArrayList<Usuario> listaUsuarios;
		static ArrayList<Estudiante> listaEstudiantes;
		static ArrayList<Curso> listaCursos;
		static ArrayList<Certificacion> listaCertificaciones;

		
		private Sistema() {
			this.fu = new FactoryUsuario();
			this.listaUsuarios = new ArrayList<Usuario>();
			this.listaEstudiantes = new ArrayList<Estudiante>();
			this.listaCursos = new ArrayList<Curso>();
			this.listaCertificaciones = new ArrayList<Certificacion>();
		}

		public static Sistema getInstance() {
			if (instancia == null) {
				instancia = new Sistema();
			}
			return instancia;
		}
	
	
	
	public void iniciar() throws FileNotFoundException {
		listaUsuarios = leerUsuarios("usuarios.txt");
		listaEstudiantes = leerEstudiantes("estudiantes.txt");
		listaCursos = leerCursos("cursos.txt");
		listaCertificaciones = leerCertificaciones("Certificaciones.txt");
		ingresarRegistros("Registros.txt");
		ingresarNotas("Notas.txt");
		crusarCertificaciones("Asignaturas_certificaciones.txt");

	}

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

	public ArrayList<Estudiante> getlistaEstudiantes() {
		return listaEstudiantes;
	}

	public ArrayList<Curso> getListaCursos() {
		return listaCursos;
	}

	public ArrayList<Certificacion> getListaCertificaciones() {
		return listaCertificaciones;
	}

}
