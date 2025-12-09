//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

/**
 * Interfaz que define el contrato para el patrón de diseño Visitor.
 * Permite definir nuevas operaciones sobre objetos de la clase Certificacion
 * sin cambiar las clases de los elementos sobre los que opera.
 */
public interface Visitor {
	
	/**
	 * Método que define la operación a realizar sobre una certificación.
	 * @param c La certificación que está siendo visitada.
	 */
	public void visit(Certificacion c);
}