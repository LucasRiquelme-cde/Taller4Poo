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

	public String getNombre() {
		return nombre;
	}

	public ArrayList<Registro> getListaRegistrosE() {
		return listaRegistrosE;
	}

	public ArrayList<Nota> getListaNotas() {
		return listaNotas;
	}
	
	public String getCarrera() {
		return carrera;
	}

	public String getSemestre() {
		return semestre;
	}

	public String getCorreo() {
		return correo;
	}
	public void setNombre(String nombre) {
        this.nombre = nombre;
    }
	
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    
    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
	
}
