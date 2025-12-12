// Matías Collao / 22.060.152-8 / ICCI
// Lucas Riquelme / 21.943.208-9 / ICCI
package taller4.Presentacion;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import taller4.Sistema;
import taller4.Dominio.Estudiante;

/**
 * Clase principal de la aplicación AcademiCore.
 * Extiende JFrame para manejar la ventana inicial de inicio de sesión (Login).
 * Es el punto de entrada del programa, inicializa el sistema y gestiona el enrutamiento
 * de usuarios según su rol (Admin, Coordinador o Estudiante).
 */
public class App extends JFrame {
	
	static Sistema s = Sistema.getInstance();
	static JFrame ventana = crearVentana();
	
	/**
	 * Método principal (Main) que inicia la ejecución del programa.
	 * Carga los datos del sistema y hace visible la ventana de login.
	 * @param args Argumentos de la línea de comandos (no utilizados).
	 * @throws FileNotFoundException Si no se encuentran los archivos de datos necesarios.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		s.iniciar();
		ventana.setVisible(true);
	}

	/**
	 * Crea y configura la interfaz gráfica de la ventana de inicio de sesión.
	 * @return Un objeto JFrame con los campos de usuario, contraseña y botón de ingreso.
	 */
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

	/**
	 * Maneja el evento de clic en el botón "Ingresar".
	 * Valida las credenciales con el Sistema y abre la ventana correspondiente al rol del usuario.
	 * @param usuario Campo de texto con el usuario/RUT.
	 * @param contraseña Campo de texto con la contraseña.
	 * @return null (retorno requerido por la interfaz funcional, no utilizado).
	 */
	private static Object accionBLogin(JTextField usuario, JTextField contraseña) {
		String opMenus = s.getMenu(usuario.getText(), contraseña.getText());
		
		if (opMenus.equals("A")) {
			new VentanaAdministrador();
			
		} else if (opMenus.equals("C")) {
		    new VentanaCoordinador(s);
		    
		} else if (opMenus.equals("E")) {
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
			JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
		}
		return null;
	}
}