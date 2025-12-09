//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

public class FactoryUsuario {
	
	public Usuario getUsuario(String nombre, String Contraseña, String rol, String info) {
		return switch (rol) {
		case "Admin" -> new Administrador(nombre, Contraseña, rol, info);
			
		case "Coordinador" -> new Coordinador(nombre, Contraseña, rol, info);
		
		default -> null;
		};
	}

}
