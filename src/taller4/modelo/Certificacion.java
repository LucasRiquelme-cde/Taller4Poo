// Matías Collao / 22.060.152-8 / ICCI
// Lucas Riquelme / 21.943.208-9 / ICCI
package taller4.modelo;

import java.util.ArrayList;

/**
 * Clase que representa una Línea de Certificación Profesional.
 * Contiene la información descriptiva de la certificación y la lista de cursos
 * que la componen.
 */
public class Certificacion {
	
	private String id, nombre, descripcion, requisitos, validez;
	private ArrayList<Curso> listaCursos;

	/**
	 * Constructor de la clase Certificacion.
	 * Inicializa los atributos y crea la lista de cursos vacía.
	 * @param id          Identificador único de la certificación.
	 * @param nombre      Nombre de la certificación.
	 * @param descripcion Descripción detallada de lo que abarca.
	 * @param requisitos  Requisitos mínimos (créditos) para obtenerla.
	 * @param validez     Tiempo de validez de la certificación.
	 */
	public Certificacion(String id, String nombre, String descripcion, String requisitos, String validez) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.requisitos = requisitos;
		this.validez = validez;
		this.listaCursos = new ArrayList<Curso>();
	}

	/**
	 * Obtiene el ID de la certificación.
	 * @return El identificador único.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Agrega un curso a la lista de asignaturas de esta certificación.
	 * @param cu Objeto Curso a agregar.
	 */
	public void agregarCurso(Curso cu) {
		this.listaCursos.add(cu);
	}

	/**
	 * Obtiene el nombre de la certificación.
	 * @return El nombre.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece un nuevo nombre para la certificación.
	 * @param nombre El nuevo nombre.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene la descripción de la certificación.
	 * @return La descripción.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece una nueva descripción.
	 * @param descripcion La nueva descripción.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtiene los requisitos (créditos) de la certificación.
	 * @return Los requisitos como cadena de texto.
	 */
	public String getRequisitos() {
		return requisitos;
	}

	/**
	 * Establece nuevos requisitos para la certificación.
	 * @param requisitos Los nuevos requisitos.
	 */
	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	/**
	 * Obtiene la validez de la certificación.
	 * @return La validez (ej: "3 años").
	 */
	public String getValidez() {
		return validez;
	}

	/**
	 * Establece una nueva validez.
	 * @param validez La nueva validez.
	 */
	public void setValidez(String validez) {
		this.validez = validez;
	}
	
	/**
	 * Obtiene la lista de cursos asociados a esta certificación.
	 * @return ArrayList de objetos Curso.
	 */
	public ArrayList<Curso> getListaCursos() {
		return listaCursos;
	}
}