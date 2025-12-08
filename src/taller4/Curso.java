package taller4;

public class Curso {
	
	private String nRc, nombre, area, prerrequisitos;
	private int semestre, creditos;
	
	public Curso(String nRc, String nombre, String area, String prerrequisitos, int semestre, int creditos) {
		this.nRc = nRc;
		this.nombre = nombre;
		this.area = area;
		this.prerrequisitos = prerrequisitos;
		this.semestre = semestre;
		this.creditos = creditos;
	}

	public String getnRc() {
		return nRc;
	}
	
	
	
}
