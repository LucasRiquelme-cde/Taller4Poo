//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

/**
 * Clase que representa el registro de inscripción de un estudiante a una certificación.
 * Vincula a un alumno con una línea de certificación específica y rastrea su estado y progreso.
 */
public class Registro {
	
	private String rut, idCertificacion, fecha, estado;
	private int progreso;
	
	/**
	 * Constructor de la clase Registro.
	 * * @param rut             RUT del estudiante que se inscribe.
	 * @param idCertificacion ID de la certificación a la que se inscribe.
	 * @param fecha           Fecha de la inscripción (formato YYYY-MM-DD).
	 * @param estado          Estado actual del registro (ej: "Activa", "Completada").
	 * @param progreso        Porcentaje de avance en la certificación (0 a 100).
	 */
	public Registro(String rut, String idCertificacion, String fecha, String estado, int progreso) {
		super();
		this.rut = rut;
		this.idCertificacion = idCertificacion;
		this.fecha = fecha;
		this.estado = estado;
		this.progreso = progreso;
	}

	/**
	 * Obtiene el ID de la certificación asociada a este registro.
	 * @return El ID de la certificación.
	 */
	public String getIdCertificacion() {
		return idCertificacion;
	}

	/**
	 * Obtiene el estado actual de la inscripción.
	 * @return El estado (ej: "Activa", "Suspendida").
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Obtiene el porcentaje de progreso de la certificación.
	 * @return Un entero representando el porcentaje (0-100).
	 */
	public int getProgreso() {
		return progreso;
	}
	
	
}