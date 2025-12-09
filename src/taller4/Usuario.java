package taller4;

public abstract class Usuario {
	
	protected String nombre, Contraseña, rol, info;

	public Usuario(String nombre, String contraseña, String rol, String info) {
		super();
		this.nombre = nombre;
		this.Contraseña = contraseña;
		this.rol = rol;
		this.info = info;
	}

	public String getNombre() {
		return nombre;
	}

	public String getContraseña() {
		return Contraseña;
	}

	public String getRol() {
		return rol;
	}
	
	public String toString() {
		return "Usuario [nombre=" + nombre + ", Contraseña=" + Contraseña + ", rol=" + rol + ", info=" + info + "]";
	}
	
	public void setNombre(String nombre) {
        this.nombre = nombre;
    }
	
    public void setContraseña(String contraseña) {
        this.Contraseña = contraseña;
    }
    
    public void setInfo(String info) {
        this.info = info;
    }
	
	
	
}
