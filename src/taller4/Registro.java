package taller4;

public class Registro {
	
	private String rut, idCertificacion, fecha, estado;
	private int progreso;
	
	public Registro(String rut, String idCertificacion, String fecha, String estado, int progreso) {
		super();
		this.rut = rut;
		this.idCertificacion = idCertificacion;
		this.fecha = fecha;
		this.estado = estado;
		this.progreso = progreso;
	}

	public String getIdCertificacion() {
		return idCertificacion;
	}

	public String getEstado() {
		return estado;
	}
	
	public int getProgreso() {
		return progreso;
	}
	
	
}