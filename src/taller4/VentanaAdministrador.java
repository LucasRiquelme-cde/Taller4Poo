//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

import java.awt.GridLayout;
import javax.swing.*;

/**
 * Ventana gráfica para el menú del Administrador.
 * Gestiona la creación, modificación y eliminación de usuarios,
 * asegurando la persistencia de datos en los archivos TXT mediante llamadas al Sistema.
 */
public class VentanaAdministrador extends JFrame {

	Sistema s = Sistema.getInstance();

	/**
	 * Constructor de la ventana de administrador.
	 * Configura el diseño y los botones principales del panel de control.
	 */
	public VentanaAdministrador() {
		setTitle("Menú Administrador - AcademiCore");
		setSize(400, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));

		JButton bCrear = new JButton("Crear Cuenta");
		JButton bModificar = new JButton("Modificar Cuenta");
		JButton bEliminar = new JButton("Eliminar Cuenta");
		JButton bRestablecer = new JButton("Restablecer Contraseña");
		JButton bSalir = new JButton("Cerrar Sesión");

		bCrear.addActionListener(e -> menuCrear());
		bModificar.addActionListener(e -> menuModificar());
		bEliminar.addActionListener(e -> menuEliminar());
		bRestablecer.addActionListener(e -> menuRestablecer());
		bSalir.addActionListener(e -> dispose());

		panel.add(bCrear);
		panel.add(bModificar);
		panel.add(bEliminar);
		panel.add(bRestablecer);
		panel.add(bSalir);

		add(panel);
		setVisible(true);
	}

	/**
	 * Despliega las opciones para crear una nueva cuenta.
	 * Permite elegir entre crear un Estudiante o un Usuario del Staff (Admin/Coordinador).
	 */
	private void menuCrear() {
		String[] opciones = {"Estudiante", "Coordinador/Admin"};
		int seleccion = JOptionPane.showOptionDialog(this, "¿Qué tipo de cuenta desea crear?", "Crear Cuenta",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		if (seleccion == 0) {
			crearEstudianteGUI();
		} else if (seleccion == 1) {
			crearStaffGUI();
		}
	}

	/**
	 * Muestra un formulario para ingresar los datos de un nuevo estudiante.
	 * Si se confirma, crea el objeto Estudiante y lo guarda en memoria y en el archivo TXT.
	 */
	private void crearEstudianteGUI() {
		JTextField tRut = new JTextField();
		JTextField tNombre = new JTextField();
		JTextField tCarrera = new JTextField();
		JTextField tSemestre = new JTextField();
		JTextField tCorreo = new JTextField();
		JTextField tPass = new JTextField();

		Object[] message = {
			"RUT:", tRut, "Nombre:", tNombre, "Carrera:", tCarrera,
			"Semestre:", tSemestre, "Correo:", tCorreo, "Contraseña:", tPass
		};

		int option = JOptionPane.showConfirmDialog(this, message, "Nuevo Estudiante", JOptionPane.OK_CANCEL_OPTION);
		
		if (option == JOptionPane.OK_OPTION) {
			Estudiante nuevo = new Estudiante(
				tRut.getText().trim(), 
				tNombre.getText().trim(), 
				tCarrera.getText().trim(), 
				tSemestre.getText().trim(), 
				tCorreo.getText().trim(), 
				tPass.getText().trim()
			);
			
			Sistema.listaEstudiantes.add(nuevo);
			s.guardarEstudianteEnArchivo(nuevo);
			JOptionPane.showMessageDialog(this, "Estudiante creado y guardado exitosamente.");
		}
	}

	/**
	 * Muestra un formulario para ingresar los datos de un nuevo usuario Staff.
	 * Utiliza la FactoryUsuario para instanciar el objeto correcto (Admin o Coordinador).
	 */
	private void crearStaffGUI() {
		JTextField tNombre = new JTextField();
		JTextField tPass = new JTextField();
		String[] roles = {"Coordinador", "Admin"};
		JComboBox<String> cRol = new JComboBox<>(roles);
		JTextField tInfo = new JTextField();

		Object[] message = {
			"Usuario:", tNombre, "Contraseña:", tPass, "Rol:", cRol, "Área (Solo Coordinador):", tInfo
		};

		int option = JOptionPane.showConfirmDialog(this, message, "Nuevo Staff", JOptionPane.OK_CANCEL_OPTION);
		
		if (option == JOptionPane.OK_OPTION) {
			String rol = (String) cRol.getSelectedItem();
			Usuario nuevo = Sistema.fu.getUsuario(
				tNombre.getText().trim(), 
				tPass.getText().trim(), 
				rol, 
				tInfo.getText().trim()
			);
			
			if (nuevo != null) {
				Sistema.listaUsuarios.add(nuevo);
				s.guardarUsuarioEnArchivo(nuevo);
				JOptionPane.showMessageDialog(this, "Usuario creado y guardado exitosamente.");
			}
		}
	}

	/**
	 * Inicia el proceso de modificación de una cuenta existente.
	 * Solicita el ID (RUT o Usuario) y busca en las listas correspondientes.
	 */
	private void menuModificar() {
		String[] opciones = {"Buscar por RUT (Estudiante)", "Buscar por Usuario (Staff)"};
		int seleccion = JOptionPane.showOptionDialog(this, "Seleccione método de búsqueda", "Modificar Cuenta",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		String id = JOptionPane.showInputDialog("Ingrese ID a buscar:");
		if (id == null) return;
		id = id.trim();

		if (seleccion == 0) {
			for (Estudiante e : Sistema.listaEstudiantes) {
				if (e.getRut().equals(id)) {
					modificarEstudianteGUI(e);
					return;
				}
			}
		} else {
			for (Usuario u : Sistema.listaUsuarios) {
				if (u.getNombre().equals(id)) {
					modificarStaffGUI(u);
					return;
				}
			}
		}
		JOptionPane.showMessageDialog(this, "Usuario no encontrado.");
	}

	/**
	 * Muestra el formulario de edición para un estudiante encontrado.
	 * Actualiza los datos en memoria y luego reescribe el archivo de estudiantes.
	 * * @param e Objeto Estudiante a modificar.
	 */
	private void modificarEstudianteGUI(Estudiante e) {
		JTextField tNombre = new JTextField(e.getNombre());
		JTextField tCarrera = new JTextField(e.getCarrera());
		JTextField tCorreo = new JTextField(e.getCorreo());

		Object[] message = {"Nombre:", tNombre, "Carrera:", tCarrera, "Correo:", tCorreo};

		int option = JOptionPane.showConfirmDialog(this, message, "Modificar Estudiante", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			e.setNombre(tNombre.getText().trim());
			e.setCarrera(tCarrera.getText().trim());
			e.setCorreo(tCorreo.getText().trim());
			
			s.actualizarArchivoEstudiantes();
			
			JOptionPane.showMessageDialog(this, "Datos actualizados en archivo.");
		}
	}

	/**
	 * Muestra el formulario de edición para un usuario Staff encontrado.
	 * Actualiza los datos en memoria y reescribe el archivo de usuarios.
	 * * @param u Objeto Usuario a modificar.
	 */
	private void modificarStaffGUI(Usuario u) {
		JTextField tInfo = new JTextField(u.getInfo());
		Object[] message = {"Info / Área:", tInfo};
		int option = JOptionPane.showConfirmDialog(this, message, "Modificar Staff", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			u.setInfo(tInfo.getText().trim());
			
			s.actualizarArchivoUsuarios();
			
			JOptionPane.showMessageDialog(this, "Datos actualizados en archivo.");
		}
	}

	/**
	 * Elimina una cuenta del sistema.
	 * Busca por ID en ambas listas, elimina el objeto de la memoria y actualiza los archivos TXT.
	 */
	private void menuEliminar() {
		String entrada = JOptionPane.showInputDialog("Ingrese RUT o Nombre de Usuario a eliminar:");
		if (entrada == null) return;
		String id = entrada.trim();

		boolean borradoEstudiante = Sistema.listaEstudiantes.removeIf(e -> e.getRut().equals(id));
		
		if (borradoEstudiante) {
			s.actualizarArchivoEstudiantes();
			JOptionPane.showMessageDialog(this, "Estudiante eliminado del archivo.");
			return;
		}
		boolean borradoUsuario = Sistema.listaUsuarios.removeIf(u -> u.getNombre().equals(id));
		
		if (borradoUsuario) {
			s.actualizarArchivoUsuarios();
			JOptionPane.showMessageDialog(this, "Usuario eliminado del archivo.");
		} else {
			JOptionPane.showMessageDialog(this, "No se encontró el usuario.");
		}
	}

	/**
	 * Permite cambiar la contraseña de un usuario o estudiante.
	 * Busca el usuario, actualiza la contraseña en memoria y guarda los cambios en el archivo.
	 */
	private void menuRestablecer() {
		String entrada = JOptionPane.showInputDialog("Ingrese RUT o Nombre de Usuario:");
		if (entrada == null) return;
		String id = entrada.trim();

		for (Estudiante e : Sistema.listaEstudiantes) {
			if (e.getRut().equals(id)) {
				String nuevaPass = JOptionPane.showInputDialog("Nueva contraseña:");
				if(nuevaPass != null) {
					e.setContraseña(nuevaPass.trim());
					s.actualizarArchivoEstudiantes();
					JOptionPane.showMessageDialog(this, "Contraseña actualizada en archivo.");
				}
				return;
			}
		}

		for (Usuario u : Sistema.listaUsuarios) {
			if (u.getNombre().equals(id)) {
				String nuevaPass = JOptionPane.showInputDialog("Nueva contraseña:");
				if(nuevaPass != null) {
					u.setContraseña(nuevaPass.trim());
					s.actualizarArchivoUsuarios();
					JOptionPane.showMessageDialog(this, "Contraseña actualizada en archivo.");
				}
				return;
			}
		}
		JOptionPane.showMessageDialog(this, "Usuario no encontrado.");
	}
}