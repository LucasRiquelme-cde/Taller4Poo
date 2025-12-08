package taller4;

import java.util.ArrayList;

public class Estudiante {
	
	private String rut, nombre, carrera, semestre, correo, contraseña;
	private ArrayList<Registro> listaRegistrosE;
	private ArrayList<Nota> listaNotas;

	public Estudiante(String rut, String nombre, String carrera, String semestre, String correo, String contraseña) {
		this.rut = rut;
		this.nombre = nombre;
		this.carrera = carrera;
		this.semestre = semestre;
		this.correo = correo;
		this.contraseña = contraseña;
		this.listaRegistrosE = new ArrayList<Registro>();
		this.listaNotas = new ArrayList<Nota>();
	}

	public String getRut() {
		return rut;
	}

	public void agregarRegistro(Registro r) {
		this.listaRegistrosE.add(r);
		
	}

	public void agregarNota(Nota n) {
		this.listaNotas.add(n);
		
	}

	public String getContraseña() {
		return contraseña;
	}
	
	
	
}
