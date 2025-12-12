// Matías Collao / 22.060.152-8 / ICCI
// Lucas Riquelme / 21.943.208-9 / ICCI
package taller4.modelo;

import java.util.ArrayList;

/**
 * Clase que representa a un Estudiante dentro del sistema académico.
 * Almacena información personal, credenciales de acceso, y mantiene el registro
 * de sus notas y certificaciones inscritas.
 */
public class Estudiante {
	
	private String rut, nombre, carrera, semestre, correo, contraseña;
	private ArrayList<Registro> listaRegistrosE;
	private ArrayList<Nota> listaNotas;

	/**
	 * Constructor de la clase Estudiante.
	 * Inicializa los datos personales y las listas de registros académicos.
	 * @param rut        RUT del estudiante (identificador único).
	 * @param nombre     Nombre completo.
	 * @param carrera    Carrera que cursa.
	 * @param semestre   Semestre actual.
	 * @param correo     Correo electrónico institucional.
	 * @param contraseña Contraseña para acceder al sistema.
	 */
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

	/**
	 * Obtiene el RUT del estudiante.
	 * @return El RUT.
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * Agrega un nuevo registro de certificación a la lista del estudiante.
	 * @param r Objeto Registro que vincula al estudiante con una certificación.
	 */
	public void agregarRegistro(Registro r) {
		this.listaRegistrosE.add(r);
	}

	/**
	 * Agrega una nota al historial académico del estudiante.
	 * @param n Objeto Nota con la calificación y detalles de la asignatura.
	 */
	public void agregarNota(Nota n) {
		this.listaNotas.add(n);
	}

	/**
	 * Obtiene la contraseña del estudiante.
	 * @return La contraseña.
	 */
	public String getContraseña() {
		return contraseña;
	}

	/**
	 * Obtiene el nombre completo del estudiante.
	 * @return El nombre.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Obtiene la lista de registros de certificaciones del estudiante.
	 * @return ArrayList de registros.
	 */
	public ArrayList<Registro> getListaRegistrosE() {
		return listaRegistrosE;
	}

	/**
	 * Obtiene la lista de notas (historial académico) del estudiante.
	 * @return ArrayList de notas.
	 */
	public ArrayList<Nota> getListaNotas() {
		return listaNotas;
	}
	
	/**
	 * Obtiene la carrera del estudiante.
	 * @return Nombre de la carrera.
	 */
	public String getCarrera() {
		return carrera;
	}

	/**
	 * Obtiene el semestre actual del estudiante.
	 * @return El semestre.
	 */
	public String getSemestre() {
		return semestre;
	}

	/**
	 * Obtiene el correo electrónico del estudiante.
	 * @return El correo.
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Modifica el nombre del estudiante.
	 * @param nombre Nuevo nombre.
	 */
	public void setNombre(String nombre) {
        this.nombre = nombre;
    }
	
	/**
	 * Modifica la carrera del estudiante.
	 * @param carrera Nueva carrera.
	 */
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    
    /**
     * Modifica el semestre actual del estudiante.
     * @param semestre Nuevo semestre.
     */
    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
    
    /**
     * Modifica el correo electrónico del estudiante.
     * @param correo Nuevo correo.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    /**
     * Modifica la contraseña del estudiante.
     * @param contraseña Nueva contraseña.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}