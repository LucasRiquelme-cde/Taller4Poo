// Matías Collao / 22.060.152-8 / ICCI
// Lucas Riquelme / 21.943.208-9 / ICCI
package taller4.servicio;

import taller4.modelo.Certificacion;

/**
 * Implementación concreta del patrón Visitor.
 * Esta clase define la operación de "Dar Consejo", la cual analiza el tipo de certificación
 * que el estudiante está cursando y ofrece una recomendación académica personalizada.
 */
public class VisitorConsejo implements Visitor {

	/**
	 * Método que visita un objeto de tipo Certificación y ejecuta la lógica de consejería.
	 * Evalúa el ID de la certificación para imprimir un mensaje específico en la consola.
	 * @param c La certificación sobre la cual se generará el consejo.
	 */
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