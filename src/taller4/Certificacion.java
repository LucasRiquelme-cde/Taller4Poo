package taller4;

import java.util.ArrayList;

public class Certificacion {
	
	private String id, nombre, descripcion, requisitos, validez;
	private ArrayList<Curso> listaCursos;

	public Certificacion(String id, String nombre, String descripcion, String requisitos, String validez) {

		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.requisitos = requisitos;
		this.validez = validez;
		this.listaCursos = new ArrayList<Curso>();
	}

	public String getId() {
		return id;
	}

	public void agregarCurso(Curso cu) {
		this.listaCursos.add(cu);
		
	}

	
	
	
	
}
