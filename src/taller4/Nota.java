package taller4;

public class Nota {
	
	private String rut, codigoAsignatura, estado, semestreCursado;
	private double calificacion;
	
	public Nota(String rut, String codigoAsignatura, String estado, String semestreCursado, double calificacion) {
		
		this.rut = rut;
		this.codigoAsignatura = codigoAsignatura;
		this.estado = estado;
		this.semestreCursado = semestreCursado;
		this.calificacion = calificacion;
	}

	public String getCodigoAsignatura() {
		return codigoAsignatura;
	}
	
	public double getCalificacion() {
		return calificacion;
	}
	
	public String getEstado() {
		return estado;
	}

	public String getSemestreCursado() {
		return semestreCursado;
	}
	
	
	
}
