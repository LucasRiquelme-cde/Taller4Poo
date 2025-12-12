//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4.Presentacion;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;

import taller4.Dominio.*;
import taller4.Logica.*;
import taller4.*;
import taller4.Dominio.Certificacion;
import taller4.Dominio.Curso;
import taller4.Dominio.Estudiante;
import taller4.Dominio.Nota;
import taller4.Dominio.Registro;
import taller4.Logica.Visitor;
import taller4.Logica.VisitorConsejo;

/**
 * Ventana que permite al estudiante realizar el seguimiento de su progreso académico.
 * Ofrece funcionalidades para ver el porcentaje de avance en certificaciones inscritas,
 * listar asignaturas pendientes y solicitar consejos académicos utilizando el patrón **Visitor**.
 */
public class VentanaProgresoEstudiante extends JFrame {
	
	Sistema s = Sistema.getInstance();
	private Estudiante estudiante;
	private JTextField tId;

	/**
	 * Constructor de la ventana de progreso.
	 * Configura los botones para las acciones de seguimiento y el campo de texto
	 * para ingresar el ID de la certificación a consultar.
	 * * @param e El estudiante que está consultando su progreso.
	 */
	public VentanaProgresoEstudiante(Estudiante e) {
		this.estudiante = e;
		
		setTitle("Mi Progreso y Pendientes");
		setSize(500, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

		JButton bVerProgreso = new JButton("Ver Mi Progreso");
		bVerProgreso.addActionListener(ev -> verProgreso());
		
		panel.add(new JLabel("Resumen de Avance:"));
		panel.add(bVerProgreso);

		panel.add(new JLabel("Ingrese ID para detalles:"));
		tId = new JTextField();
		panel.add(tId);

		JButton bPendientes = new JButton("Ver Asignaturas Pendientes");
		bPendientes.addActionListener(ev -> verPendientes());
		
		JButton bConsejo = new JButton("Solicitar Consejo");
		bConsejo.addActionListener(ev -> usarVisitor());

		panel.add(bPendientes);
		panel.add(bConsejo);
		
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));

		add(panel);
		setVisible(true);
	}

	/**
	 * Calcula y muestra el porcentaje de avance de las certificaciones inscritas.
	 * Compara la cantidad de cursos aprobados vs el total de cursos de la certificación
	 * e imprime el resultado en la consola.
	 */
	public void verProgreso() {
		System.out.println("Dashboard de Progreso");
		ArrayList<Registro> registros = estudiante.getListaRegistrosE();
		
		if (registros.isEmpty()) {
			System.out.println("No tienes certificaciones inscritas.");
			return;
		}

		for (Registro r : registros) {
			int cursosAprobados = calcularCursosAprobados(r.getIdCertificacion());
			int totalCursos = contarTotalCursos(r.getIdCertificacion());
			
			double porcentaje = 0;
			if (totalCursos > 0) {
				porcentaje = (cursosAprobados * 100.0) / totalCursos;
			}
			
			System.out.println("Certificación: " + r.getIdCertificacion());
			System.out.println("Estado: " + r.getEstado());
			System.out.println("Progreso Real: " + (int)porcentaje + "% (" + cursosAprobados + "/" + totalCursos + " cursos)");
			System.out.println("");
		}
	}

	/**
	 * Identifica y lista las asignaturas pendientes para una certificación específica.
	 * Busca el ID ingresado y verifica qué cursos de esa certificación no tienen
	 * una nota aprobatoria en el historial del estudiante.
	 */
	public void verPendientes() {
		String id = tId.getText();
		System.out.println("Analizando pendientes para: " + id);
		
		Certificacion cert = buscarCertificacion(id);
		if (cert == null) {
			System.out.println("ID no encontrado o no válido.");
			return;
		}
		ArrayList<Curso> cursosRequeridos = cert.getListaCursos();
		boolean tienePendientes = false;

		for (Curso c : cursosRequeridos) {
			boolean aprobado = false;
			for (Nota n : estudiante.getListaNotas()) {
				if (n.getCodigoAsignatura().equals(c.getnRc())) {
					if (n.getCalificacion() >= 4.0 || n.getEstado().equalsIgnoreCase("Aprobada")) {
						aprobado = true;
					}
				}
			}
			
			if (!aprobado) {
				System.out.println("Pendiente " + c.getNombre() + " Créditos: " + c.getCreditos());
				tienePendientes = true;
			}
		}

		if (!tienePendientes) {
			System.out.println("Felicidades Has completado todas las asignaturas.");
		}
		System.out.println("");
	}

	/**
	 * Ejecuta el patrón de diseño Visitor.
	 * Crea una instancia de VisitorConsejo y "visita" la certificación seleccionada
	 * para generar una recomendación académica personalizada.
	 */
	public void usarVisitor() {
		String id = tId.getText();
		Certificacion cert = buscarCertificacion(id);
		
		if (cert != null) {
			Visitor v = new VisitorConsejo();
			v.visit(cert);
		} else {
			System.out.println("Certificación no encontrada para dar consejo.");
		}
	}

	/**
	 * Busca una certificación en el sistema por su ID.
	 * @param id Identificador de la certificación.
	 * @return Objeto Certificacion si existe, null si no.
	 */
	private Certificacion buscarCertificacion(String id) {
		ArrayList<Certificacion> listaCe = s.getListaCertificaciones();
		for (Certificacion c : listaCe) {
			if (c.getId().equalsIgnoreCase(id)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Cuenta el total de cursos que componen una certificación.
	 * @param idCert ID de la certificación.
	 * @return Cantidad de cursos.
	 */
	private int contarTotalCursos(String idCert) {
		Certificacion c = buscarCertificacion(idCert);
		if (c != null) {
			return c.getListaCursos().size();
		}
		return 0;
	}

	/**
	 * Calcula cuántos cursos de una certificación específica han sido aprobados por el estudiante.
	 * @param idCert ID de la certificación.
	 * @return Cantidad de cursos aprobados.
	 */
	private int calcularCursosAprobados(String idCert) {
		Certificacion c = buscarCertificacion(idCert);
		if (c == null) return 0;
		
		int aprobados = 0;
		for (Curso curso : c.getListaCursos()) {
			for (Nota n : estudiante.getListaNotas()) {
				if (n.getCodigoAsignatura().equals(curso.getnRc())) {
					if (n.getCalificacion() >= 4.0 || n.getEstado().equalsIgnoreCase("Aprobada")) {
						aprobados++;
					}
				}
			}
		}
		return aprobados;
	}
}