//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

/**
 * Clase fábrica encargada de la creación de objetos de tipo Usuario.
 * Implementa el patrón de diseño Factory Method para centralizar la instanciación
 * [cite_start]de usuarios según su rol (Administrador o Coordinador)[cite: 34].
 */
public class FactoryUsuario {
	
	/**
	 * Genera y devuelve una instancia concreta de Usuario basada en el rol proporcionado.
	 * * @param nombre     Nombre de usuario.
	 * @param Contraseña Contraseña del usuario.
	 * @param rol        Rol del usuario ("Admin" o "Coordinador").
	 * @param info       Información adicional específica del rol (ej: área de coordinación).
	 * @return Una instancia de {@link Administrador} o {@link Coordinador}, o null si el rol no es reconocido.
	 */
	public Usuario getUsuario(String nombre, String Contraseña, String rol, String info) {
		return switch (rol) {
		case "Admin" -> new Administrador(nombre, Contraseña, rol, info);
			
		case "Coordinador" -> new Coordinador(nombre, Contraseña, rol, info);
		
		default -> null;
		};
	}

}