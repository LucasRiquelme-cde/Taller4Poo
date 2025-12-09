//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.*;

/**
 * Ventana que permite a un estudiante inscribirse en nuevas líneas de certificación.
 * Gestiona la validación de prerrequisitos (créditos acumulados), verifica duplicidad
 * de inscripciones y muestra el catálogo disponible.
 */
public class VentanaInscripcionCertificaciones extends JFrame {
	
	Sistema s = Sistema.getInstance();
	
	private Estudiante estudiante;
	private JTextField tId;

	/**
	 * Constructor de la ventana de inscripción.
	 * Inicializa la interfaz gráfica con los campos para ingresar el ID de la certificación
	 * y los botones para listar, ver detalles e inscribirse.
	 * * @param e Objeto Estudiante que está realizando la inscripción.
	 */
	public VentanaInscripcionCertificaciones(Estudiante e) {
		this.estudiante = e;
		
		setTitle("Inscripción de Certificaciones");
		setSize(450, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

		JButton bListar = new JButton("Listar Disponibles");
		bListar.addActionListener(ev -> listarCertificaciones());
		
		panel.add(new JLabel("Ver Catálogo"));
		panel.add(bListar);

		panel.add(new JLabel("Ingrese ID Certificación:"));
		tId = new JTextField();
		panel.add(tId);

		JButton bDetalles = new JButton("Ver Requisitos");
		bDetalles.addActionListener(ev -> verDetalles());
		
		JButton bInscribir = new JButton("Inscribir Ahora");
		bInscribir.addActionListener(ev -> inscribir());

		panel.add(bDetalles);
		panel.add(bInscribir);
		
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));

		add(panel);
		setVisible(true);
	}

	/**
	 * Muestra en la consola el catálogo completo de certificaciones disponibles en el sistema.
	 * Imprime el ID y el nombre de cada una.
	 */
	public void listarCertificaciones() {
		System.out.println("Catálogo de Certificaciones Disponibles");
		ArrayList<Certificacion> listaCe = s.getListaCertificaciones();
		for (Certificacion c : listaCe) {
			System.out.println("ID: " + c.getId() + "  Nombre: " + c.getNombre());
		}
		System.out.println("");
	}

	/**
	 * Busca una certificación por el ID ingresado en el campo de texto y muestra sus detalles.
	 * Imprime descripción, requisitos de créditos y validez.
	 */
	public void verDetalles() {
		String id = tId.getText();
		boolean encontrada = false;
		ArrayList<Certificacion> listaCe = s.getListaCertificaciones();
		for (Certificacion c : listaCe) {
			if (c.getId().equalsIgnoreCase(id)) {
				System.out.println("Detalles de: " + c.getNombre());
				System.out.println("Descripción: " + c.getDescripcion());
				System.out.println("Créditos Requeridos: " + c.getRequisitos());
				System.out.println("Validez: " + c.getValidez());
				System.out.println("");
				encontrada = true;
			}
		}
		if (!encontrada) {
			System.out.println("ID no encontrada.");
		}
	}

	/**
	 * Ejecuta el proceso de inscripción.
	 * 1. Verifica que la certificación exista.
	 * 2. Verifica que el estudiante no esté ya inscrito.
	 * 3. Valida que el estudiante cumpla con los créditos mínimos requeridos.
	 * Si todo es correcto, crea un nuevo registro con fecha actual.
	 */
	public void inscribir() {
		String id = tId.getText();
		Certificacion certAInscribir = null;
		ArrayList<Certificacion> listaCe = s.getListaCertificaciones();
		for (Certificacion c : listaCe) {
			if (c.getId().equalsIgnoreCase(id)) {
				certAInscribir = c;
				break;
				
				
			}
		}

		if (certAInscribir == null) {
			System.out.println("Error: Certificación no encontrada.");
			return;
		}

		for (Registro r : estudiante.getListaRegistrosE()) {
			if (r.getIdCertificacion().equalsIgnoreCase(id)) {
				System.out.println("Error: Ya estás inscrito en esta certificación.");
				return;
			}
		}

		int creditosRequeridos = Integer.valueOf(certAInscribir.getRequisitos());
		int creditosAlumno = calcularCreditosAlumno();
		
		System.out.println("Tus Créditos: " + creditosAlumno);
		System.out.println("Necesarios: " + creditosRequeridos);

		if (creditosAlumno >= creditosRequeridos) {
			String fechaHoy = LocalDate.now().toString(); 
			Registro nuevoRegistro = new Registro(estudiante.getRut(), id, fechaHoy, "Activa", 0);
			estudiante.agregarRegistro(nuevoRegistro);
			System.out.println("Inscripción realizada correctamente.");
		} else {
			System.out.println("No cumples con los créditos mínimos.");
		}
	}

	/**
	 * Calcula el total de créditos SCT acumulados por el estudiante.
	 * Solo suma los créditos de asignaturas aprobadas (nota >= 4.0 o estado "Aprobada").
	 * * @return Total de créditos aprobados.
	 */
	private int calcularCreditosAlumno() {
		int totalCreditos = 0;
		for (Nota n : estudiante.getListaNotas()) {
			if (n.getCalificacion() >= 4.0 || n.getEstado().equalsIgnoreCase("Aprobada")) {
				for (Curso c : Sistema.getInstance().getListaCursos()) {
					if (c.getnRc().equals(n.getCodigoAsignatura())) {
						totalCreditos += c.getCreditos();
					}
				}
			}
		}
		return totalCreditos;
	}
}