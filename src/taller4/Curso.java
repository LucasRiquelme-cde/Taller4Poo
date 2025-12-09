//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

/**
 * Clase que representa una Asignatura o Curso dentro del sistema académico.
 * Contiene la información administrativa y académica del curso.
 */
public class Curso {
	
	private String nRc, nombre, area, prerrequisitos;
	private int semestre, creditos;
	
	/**
	 * Constructor de la clase Curso.
	 * * @param nRc            Código único de la asignatura (NRC).
	 * @param nombre         Nombre oficial de la asignatura.
	 * @param area           Área de conocimiento o departamento al que pertenece.
	 * @param prerrequisitos Códigos de los cursos necesarios previos (separados por comas o vacío).
	 * @param semestre       Número de semestre en la malla curricular.
	 * @param creditos       Cantidad de créditos SCT que otorga el curso.
	 */
	public Curso(String nRc, String nombre, String area, String prerrequisitos, int semestre, int creditos) {
		this.nRc = nRc;
		this.nombre = nombre;
		this.area = area;
		this.prerrequisitos = prerrequisitos;
		this.semestre = semestre;
		this.creditos = creditos;
	}

	/**
	 * Obtiene el NRC (Identificador único) del curso.
	 * @return El código NRC.
	 */
	public String getnRc() {
		return nRc;
	}

	/**
	 * Obtiene el nombre de la asignatura.
	 * @return El nombre.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Obtiene el semestre en el que se dicta el curso.
	 * @return El número de semestre.
	 */
	public int getSemestre() {
		return semestre;
	}

	/**
	 * Obtiene la cantidad de créditos del curso.
	 * @return Los créditos SCT.
	 */
	public int getCreditos() {
		return creditos;
	}
	
	/**
	 * Obtiene los prerrequisitos necesarios para este curso.
	 * @return Cadena con los códigos de prerrequisitos.
	 */
	public String getPrerrequisitos() {
		return prerrequisitos;
	}

	/**
	 * Obtiene el área académica a la que pertenece el curso.
	 * @return El nombre del área.
	 */
	public String getArea() {
		return area;
	}
	
}