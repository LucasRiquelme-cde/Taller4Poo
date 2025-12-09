//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

/**
 * Clase que representa a un usuario con rol de Administrador en el sistema.
 * Hereda de la clase abstracta Usuario.
 */
public class Administrador extends Usuario {

	/**
	 * Constructor de la clase Administrador.
	 * * @param nombre     Nombre de usuario del administrador.
	 * @param contraseña Contraseña de acceso al sistema.
	 * @param rol        Rol asignado (debe ser "Admin" o similar).
	 * @param info       Información adicional (puede estar vacío para este rol).
	 */
	public Administrador(String nombre, String contraseña, String rol, String info) {
		super(nombre, contraseña, rol, info);
	}

}