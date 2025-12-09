package taller4;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaProgresoEstudiante extends JFrame {
	Sistema s = Sistema.getInstance();
	private Estudiante estudiante;
	private JTextField tId;

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

	private Certificacion buscarCertificacion(String id) {
		ArrayList<Certificacion> listaCe = s.getListaCertificaciones();
		for (Certificacion c : listaCe) {
			if (c.getId().equalsIgnoreCase(id)) {
				return c;
			}
		}
		return null;
	}

	private int contarTotalCursos(String idCert) {
		Certificacion c = buscarCertificacion(idCert);
		if (c != null) {
			return c.getListaCursos().size();
		}
		return 0;
	}

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