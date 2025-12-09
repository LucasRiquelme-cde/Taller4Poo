//MatíasCollao/22.060.152-8/ICCI//LucasRiquelme/21.943.208-9/ICCI
package taller4;

import java.awt.*;
import java.io.*;
import java.util.*;

import javax.swing.*;

public class App extends JFrame {
	
	static Sistema s = Sistema.getInstance();
	static JFrame ventana = crearVentana();
	
	public static void main(String[] args) throws FileNotFoundException {
		s.iniciar();
		ventana.setVisible(true);
	}

	private static JFrame crearVentana() {
		JFrame ventana = new JFrame("AcademiCore");
		ventana.setSize(450, 300);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panelLogin = new JPanel(new GridLayout(3, 2, 10, 10));
		JLabel textoLogin = new JLabel("Bienvenido");
		JButton botonLogin = new JButton("Ingresar");
		
		JLabel usuarioLogin = new JLabel("Ingrese Usuario: ");
		JTextField usuario = new JTextField();
		JLabel contraseñaLogin = new JLabel("Ingrese Contraseña: ");
		JTextField contraseña = new JTextField();
		botonLogin.addActionListener(e -> accionBLogin(usuario, contraseña));
		
		panelLogin.add(textoLogin);
		panelLogin.add(botonLogin);
		panelLogin.add(usuarioLogin);
		panelLogin.add(usuario);
		panelLogin.add(contraseñaLogin);
		panelLogin.add(contraseña);
		
		ventana.add(panelLogin);
		return ventana;
	}

	private static Object accionBLogin(JTextField usuario, JTextField contraseña) {
		String opMenus = s.getMenu(usuario.getText(), contraseña.getText());
		
		if (opMenus.equals("A")) {
			System.out.println("Bienvenido Administrador");
			int op = -1;
			while (true) {
				printMenuAdmin();
				op = escanearDesdeTeclado();
				
				if (op == 0) {
					System.out.println("--- Crear Cuenta ---");
					System.out.println("1) Estudiante");
					System.out.println("2) Coordinador/Admin");
					int tipo = escanearDesdeTeclado();
					
					if(tipo == 1) {
						System.out.println("Ingrese RUT:");
						String rut = escanearString();
						System.out.println("Ingrese Nombre:");
						String nom = escanearString();
						System.out.println("Ingrese Carrera:");
						String carr = escanearString();
						System.out.println("Ingrese Semestre:");
						String sem = escanearString();
						System.out.println("Ingrese Correo:");
						String mail = escanearString();
						System.out.println("Ingrese Contraseña:");
						String pass = escanearString();
						
						Estudiante nuevo = new Estudiante(rut, nom, carr, sem, mail, pass);
						Sistema.listaEstudiantes.add(nuevo);
						System.out.println("Estudiante creado con éxito.");
						
					} else if (tipo == 2) {
						System.out.println("Ingrese Nombre Usuario:");
						String nom = escanearString();
						System.out.println("Ingrese Contraseña:");
						String pass = escanearString();
						System.out.println("Rol (Coordinador / Admin):");
						String rol = escanearString();
						String info = "";
						if(rol.equalsIgnoreCase("Coordinador")) {
							System.out.println("Ingrese Área:");
							info = escanearString();
						}
						
						Usuario nuevoU = Sistema.fu.getUsuario(nom, pass, rol, info);
						if(nuevoU != null) {
							Sistema.listaUsuarios.add(nuevoU);
							System.out.println("Usuario creado con éxito.");
						} else {
							System.out.println("Error en rol ingresado.");
						}
					}
					
				} else if (op == 1) {
					System.out.println("--- Modificar Cuenta ---");
					System.out.println("1) Buscar Estudiante (RUT)");
					System.out.println("2) Buscar Staff (Nombre Usuario)");
					int tipo = escanearDesdeTeclado();
					
					if(tipo == 1) {
						System.out.println("Ingrese RUT a buscar:");
						String rut = escanearString();
						Estudiante eEncontrado = null;
						for(Estudiante e : Sistema.listaEstudiantes) {
							if(e.getRut().equals(rut)) {
								eEncontrado = e;
							}
						}
						if(eEncontrado != null) {
							System.out.println("Ingrese Nuevo Nombre:");
							eEncontrado.setNombre(escanearString());
							System.out.println("Ingrese Nueva Carrera:");
							eEncontrado.setCarrera(escanearString());
							System.out.println("Ingrese Nuevo Correo:");
							eEncontrado.setCorreo(escanearString());
							System.out.println("Datos actualizados.");
						} else {
							System.out.println("Estudiante no encontrado.");
						}
						
					} else if (tipo == 2) {
						System.out.println("Ingrese Nombre Usuario a buscar:");
						String nom = escanearString();
						Usuario uEncontrado = null;
						for(Usuario u : Sistema.listaUsuarios) {
							if(u.getNombre().equals(nom)) {
								uEncontrado = u;
							}
						}
						if(uEncontrado != null) {
							System.out.println("Ingrese Nuevo Info/Area (o Enter para mantener):");
							String info = escanearString();
							if(!info.isEmpty()) uEncontrado.setInfo(info);
							System.out.println("Datos actualizados.");
						} else {
							System.out.println("Usuario no encontrado.");
						}
					}

				} else if (op == 2) {
					System.out.println("--- Eliminar Cuenta ---");
					System.out.println("1) Estudiante");
					System.out.println("2) Staff");
					int tipo = escanearDesdeTeclado();
					
					if(tipo == 1) {
						System.out.println("Ingrese RUT a eliminar:");
						String rut = escanearString();
						Estudiante aBorrar = null;
						for(Estudiante e : Sistema.listaEstudiantes) {
							if(e.getRut().equals(rut)) aBorrar = e;
						}
						if(aBorrar != null) {
							Sistema.listaEstudiantes.remove(aBorrar);
							System.out.println("Estudiante eliminado.");
						} else {
							System.out.println("No encontrado.");
						}
					} else if(tipo == 2) {
						System.out.println("Ingrese Nombre Usuario a eliminar:");
						String nom = escanearString();
						Usuario aBorrar = null;
						for(Usuario u : Sistema.listaUsuarios) {
							if(u.getNombre().equals(nom)) aBorrar = u;
						}
						if(aBorrar != null) {
							Sistema.listaUsuarios.remove(aBorrar);
							System.out.println("Usuario eliminado.");
						} else {
							System.out.println("No encontrado.");
						}
					}

				} else if (op == 3) {
					System.out.println("--- Restablecer Contraseña ---");
					System.out.println("Ingrese Usuario o RUT:");
					String id = escanearString();
					boolean encontrado = false;
					
					for(Estudiante e : Sistema.listaEstudiantes) {
						if(e.getRut().equals(id)) {
							System.out.println("Ingrese nueva contraseña:");
							e.setContraseña(escanearString());
							encontrado = true;
							System.out.println("Contraseña actualizada.");
						}
					}
					if(!encontrado) {
						for(Usuario u : Sistema.listaUsuarios) {
							if(u.getNombre().equals(id)) {
								System.out.println("Ingrese nueva contraseña:");
								u.setContraseña(escanearString());
								encontrado = true;
								System.out.println("Contraseña actualizada.");
							}
						}
					}
					if(!encontrado) System.out.println("Usuario no existe.");
					
				} else if (op == 4) {
					System.out.println("Cerrando sesión de administrador...");
					break;
				}
			}
			
		} else if (opMenus.equals("C")) {
		    VentanaCoordinador ventana = new VentanaCoordinador(s);
		    
		} else if (opMenus.equals("E")) {
		    System.out.println("Bienvenido Estudiante");
		    
		    Estudiante estudianteLogueado = null;
		    for(Estudiante est : s.getlistaEstudiantes()) { 
		        if(est.getRut().equals(usuario.getText())) {
		            estudianteLogueado = est;
		            break;
		        }
		    }
		    
		    if (estudianteLogueado != null) {
		        new VentanaEstudiante(estudianteLogueado);
		    }
		
		} else {
			System.out.println("Usuario o contraseña incorrectos");
		}
		return null;
	}

	private static void printMenuAdmin() {
		System.out.println("\n--- MENÚ ADMINISTRADOR ---");
		System.out.println("0) Crear cuentas de estudiante o coordinador");
		System.out.println("1) Modificar cuentas de estudiante o coordinador");
		System.out.println("2) Eliminar cuentas de estudiante o coordinador");
		System.out.println("3) Restablecer contraseña");
		System.out.println("4) Salir");
		System.out.print("Seleccione opción: ");
	}

	private static int escanearDesdeTeclado() {
		Scanner s = new Scanner(System.in);
		int op = -1;
		try {
			op = s.nextInt();
		} catch (Exception e) {
			System.out.println("Dato invalido: " + e.getMessage());
		}
		return op;
	}
	
	private static String escanearString() {
		Scanner s = new Scanner(System.in);
		String texto = "";
		try {
			texto = s.nextLine();
		} catch (Exception e) {
			System.out.println("Error leyendo texto");
		}
		return texto;
	}

}