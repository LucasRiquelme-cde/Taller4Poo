//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

public class VisitorConsejo implements Visitor {

	@Override
	public void visit(Certificacion c) {
		System.out.println("Consejo del sistema para: " + c.getNombre());
		
		if (c.getId().equals("CERT-001")) {
			System.out.println("Sugerencia: Reforzar matemáticas y lógica para IA.");
		} else if (c.getId().equals("CERT-002")) {
			System.out.println("Sugerencia: Practicar en entornos seguros y revisar redes.");
		} else if (c.getId().equals("CERT-003")) {
			System.out.println("Sugerencia: Crear un portafolio en GitHub con proyectos reales.");
		} else {
			System.out.println("Sugerencia: Mantener constancia en el estudio.");
		}
		System.out.println("");
	}
}