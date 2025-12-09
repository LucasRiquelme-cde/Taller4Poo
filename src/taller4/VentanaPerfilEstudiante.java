package taller4;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaPerfilEstudiante extends JFrame {

	private Estudiante estudiante;

	public VentanaPerfilEstudiante(Estudiante e) {
		this.estudiante = e;
		
		setTitle("Mi Perfil Académico");
		setSize(400, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));

		
		panel.add(new JLabel("Nombre:"));
		panel.add(new JLabel(estudiante.getNombre()));

		panel.add(new JLabel("RUT:"));
		panel.add(new JLabel(estudiante.getRut()));

		panel.add(new JLabel("Carrera:"));
		panel.add(new JLabel(estudiante.getCarrera()));

		panel.add(new JLabel("Correo:"));
		panel.add(new JLabel(estudiante.getCorreo()));

		panel.add(new JLabel("Promedio General:"));
		panel.add(new JLabel(String.valueOf(calcularPromedioGeneral())));
		
		JButton bSemestral = new JButton("Ver en Consola");
		bSemestral.addActionListener(event -> calcularPromedioSemestral());
		
		panel.add(new JLabel("Detalle Semestral:"));
		panel.add(bSemestral);

		add(panel);
		setVisible(true);
	}

	private double calcularPromedioGeneral() {
		ArrayList<Nota> notas = estudiante.getListaNotas();
		if (notas.isEmpty()) {
			return 0.0;
		}

		double suma = 0;
		int cantidad = 0;
		
		for (Nota n : notas) {
			if (n.getCalificacion() > 0) {
				suma += n.getCalificacion();
				cantidad++;
			}
		}
		
		if (cantidad == 0) {
			return 0.0;
		}
		return Math.round((suma / cantidad) * 100.0) / 100.0;
	}

	private void calcularPromedioSemestral() {
		System.out.println("Promedios por Semestre para: " + estudiante.getNombre());
		ArrayList<Nota> notas = estudiante.getListaNotas();
		
		for (int i = 1; i <= 12; i++) {
			double suma = 0;
			int cantidad = 0;
			String semestreActual = String.valueOf(i);
			for (Nota n : notas) {
				if (n.getSemestreCursado().equals(n.getSemestreCursado())) { 
				}
			}
		}
		

		for (Nota n : notas) {
			System.out.println("Semestre: " + n.getSemestreCursado() + "  Asignatura: " + n.getCodigoAsignatura() + "  Nota: " + n.getCalificacion());
		}
		System.out.println("");
	}
}