//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

/**
 * Clase que representa una calificación obtenida por un estudiante en una asignatura específica.
 * Almacena el detalle del rendimiento académico, incluyendo la nota, el estado de aprobación
 * y el semestre en que se cursó.
 */
public class Nota {
	
	private String rut, codigoAsignatura, estado, semestreCursado;
	private double calificacion;
	
	/**
	 * Constructor de la clase Nota.
	 * * @param rut              RUT del estudiante asociado a esta nota.
	 * @param codigoAsignatura Código (NRC o ID) de la asignatura evaluada.
	 * @param estado           Estado final de la asignatura (ej: "Aprobada", "Reprobada", "Cursando").
	 * @param semestreCursado  Semestre académico en el que se obtuvo la calificación (ej: "2024-1").
	 * @param calificacion     Valor numérico de la nota (ej: 6.5).
	 */
	public Nota(String rut, String codigoAsignatura, String estado, String semestreCursado, double calificacion) {
		
		this.rut = rut;
		this.codigoAsignatura = codigoAsignatura;
		this.estado = estado;
		this.semestreCursado = semestreCursado;
		this.calificacion = calificacion;
	}

	/**
	 * Obtiene el código de la asignatura asociada a la nota.
	 * @return El código de la asignatura.
	 */
	public String getCodigoAsignatura() {
		return codigoAsignatura;
	}
	
	/**
	 * Obtiene el valor numérico de la calificación.
	 * @return La nota como valor decimal.
	 */
	public double getCalificacion() {
		return calificacion;
	}
	
	/**
	 * Obtiene el estado de la asignatura.
	 * @return El estado (Aprobada/Reprobada/etc).
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Obtiene el semestre en el que se cursó la asignatura.
	 * @return El semestre cursado.
	 */
	public String getSemestreCursado() {
		return semestreCursado;
	}
	
	
	
}