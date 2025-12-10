//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

/**
 * Clase abstracta que define la estructura base para los usuarios del staff (Administradores y Coordinadores).
 * Contiene los atributos comunes de autenticación y rol.
 */
public abstract class Usuario {
	
	protected String nombre, Contraseña, rol, info;

	/**
	 * Constructor de la clase Usuario.
	 * @param nombre     Nombre de usuario (login).
	 * @param contraseña Contraseña de acceso.
	 * @param rol        Rol del usuario ("Admin" o "Coordinador").
	 * @param info       Información extra (ej: Área de coordinación).
	 */
	public Usuario(String nombre, String contraseña, String rol, String info) {
		super();
		this.nombre = nombre;
		this.Contraseña = contraseña;
		this.rol = rol;
		this.info = info;
	}

	/**
	 * Obtiene el nombre de usuario.
	 * @return El nombre de usuario.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Obtiene la contraseña del usuario.
	 * @return La contraseña.
	 */
	public String getContraseña() {
		return Contraseña;
	}

	/**
	 * Obtiene el rol del usuario.
	 * @return El rol ("Admin" o "Coordinador").
	 */
	public String getRol() {
		return rol;
	}
	
	/**
	 * Obtiene la información adicional (ej: Área).
	 * Necesario para guardar los datos en el archivo de texto.
	 * @return La información extra.
	 */
	public String getInfo() {
		return info;
	}
	
	/**
	 * Retorna una representación en cadena del objeto Usuario.
	 * @return String con los detalles del usuario.
	 */
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", Contraseña=" + Contraseña + ", rol=" + rol + ", info=" + info + "]";
	}
	
	/**
	 * Modifica el nombre de usuario.
	 * @param nombre Nuevo nombre.
	 */
	public void setNombre(String nombre) {
        this.nombre = nombre;
    }
	
	/**
	 * Modifica la contraseña del usuario.
	 * @param contraseña Nueva contraseña.
	 */
    public void setContraseña(String contraseña) {
        this.Contraseña = contraseña;
    }
    
    /**
     * Modifica la información adicional del usuario.
     * @param info Nueva información.
     */
    public void setInfo(String info) {
        this.info = info;
    }
}