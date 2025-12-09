//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

/**
 * Clase que representa a un usuario con rol de Coordinador en el sistema.
 * Hereda de la clase abstracta Usuario.
 * Este tipo de usuario tiene responsabilidades de gestión sobre líneas de certificación específicas.
 */
public class Coordinador extends Usuario {

	/**
	 * Constructor de la clase Coordinador.
	 * * @param nombre     Nombre de usuario del coordinador.
	 * @param contraseña Contraseña de acceso al sistema.
	 * @param rol        Rol asignado (debe ser "Coordinador").
	 * @param info       Información adicional, corresponde al Área de coordinación (ej: "Sistemas Inteligentes").
	 */
	public Coordinador(String nombre, String contraseña, String rol, String info) {
		super(nombre, contraseña, rol, info);
	}

}