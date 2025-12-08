package taller4;

import java.awt.GridLayout;
import java.awt.print.Printable;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class App extends JFrame {
	
	static Sistema s = new Sistema();
	static JFrame ventana = crearVentana();
	public static void main(String[] args) throws FileNotFoundException {
		s.iniciar();
		
		ventana.setVisible(true);

	}
	private static JFrame crearVentana() {
		JFrame ventana = new JFrame("AcademiCore");
		ventana.setSize(750,600);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panelLogin = new JPanel(new GridLayout(3, 2));
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
			System.out.println("Bienvenido administrador");
			int op = -1;
			while (true) {
				printMenuAdmin();
				op = escanearDesdeTeclado();
				
				if (op == 0) {
					
				} else if (op == 1) {
					
				} else if (op == 2) {
					
				} else if (op == 3) {
					
				} else if (op == 4) {
					break;
				}
				
			}
			
			
			
		} else if (opMenus.equals("C")) {
			System.out.println("Bienvenido Coordinador");
			int op = -1;
			while (true) {
				printMenuUsuario();
				op = escanearDesdeTeclado();
				
				if (op == 0) {
					
				} else if (op == 1) {
					
				} else if (op == 2) {
					
				} else if (op == 3) {
					
				} else if (op == 4) {
					
				} else if (op == 5) {
					
				} else if (op == 6) {
					break;
				}
				
			}
			
		} else if (opMenus.equals("E")) {
			System.out.println("Bienvenido Estudiante");
			int op = -1;
			while (true) {
				printMenuEstudiante();
				op = escanearDesdeTeclado();
				
				if (op == 0) {
					
				} else if (op == 1) {
					
				} else if (op == 2) {
					
				} else if (op == 3) {
					
				} else if (op == 4) {
					
				} else if (op == 5) {
					
				} else if (op == 6) {
					break;
				}
				
			}
			
		} else {
			System.out.println("Usuario o contraseña incorrectos");
		}
		return null;
	
	
	}
	private static void printMenuEstudiante() {
		System.out.println("-Perfil del Estudiante\r\n"
				+ "0) Visualizar información personal completa\r\n"
				+ "1) Mostrar malla curricular con asignaturas aprobadas, reprobadas y pendientes\r\n"
				+ "2) Calcular y mostrar promedio general y por semestre\r\n"
				+ "-Malla Curricular Interactiva\r\n"
				+ "3) Visualización gráfica de la malla curricular por semestres\r\n"
				+ "4) Indicadores visuales de estado de asignaturas (colores diferentes)\r\n"
				+ "5) Información detallada al hacer clic en cada asignatura\r\n"
				+ "-Inscripción a Certificaciones\r\n"
				+ "6) Listar líneas de certificación disponibles\r\n"
				+ "7) Mostrar requisitos y descripción de cada línea\r\n"
				+ "8) Proceso de inscripción con validaciones\r\n"
				+ "9) Verificar prerrequisitos académicos\r\n"
				+ "-Seguimiento de Progreso"
				+ "10) Dashboard personal con progreso en certificaciones inscritas\r\n"
				+ "11) Aplicar Visitor para diferentes acciones según tipo de certificación\r\n"
				+ "12) Mostrar asignaturas pendientes para completar certificaciones");
		
	}
	private static void printMenuUsuario() {
		System.out.println("-Gestión de Certificaciones\r\n"
				+ "0) Modificar línea de certificación\r\n"
				+ "1) Generar certificados de estudiantes que hayan completado la línea de certificación\r\n"
				+ "-Panel de Métricas y Análisis\r\n"
				+ "2) Mostrar estadísticas de inscripciones de línea de certificación\r\n"
				+ "3) Análisis de asignaturas críticas en la línea de certificación\r\n"
				+ "-Gestión de Estudiantes\r\n"
				+ "4) Consultar perfiles completos de estudiantes de la línea de certificación\r\n"
				+ "5) Revisar y validar avances académicos"
				+ "6) Salir");
		
	}
	private static void printMenuAdmin() {
		System.out.println("0) Crear cuentas de estudiante o coordinador\r\n"
				+ "1) Modificar cuentas de estudiante o coordinador\r\n"
				+ "2) Eliminar cuentas de estudiante o coordinador\r\n"
				+ "3) Restablecer contraseña\r\n"
				+ "4) Salir");
		
	}
	private static int escanearDesdeTeclado() {
		Scanner s = new Scanner(System.in);
		int op = -1;
		try {
			op = s.nextInt();
		} catch (Exception e) {
			System.out.println("Dato invalido " + e.getMessage());
		}
		return op;
	}

}
