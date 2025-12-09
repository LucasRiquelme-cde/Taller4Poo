package taller4;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaEstadisticasInscripciones extends JFrame {

	public VentanaEstadisticasInscripciones() {
		setTitle("Estadísticas de Inscripciones");
		setSize(400, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

		JLabel lTitulo = new JLabel("Métricas de Inscripción");
		JLabel lInfo = new JLabel("Presione el botón para ver el conteo por certificación.");
		
		JButton bVer = new JButton("Mostrar Estadísticas");
		bVer.addActionListener(e -> mostrarEstadisticas());

		panel.add(lTitulo);
		panel.add(lInfo);
		panel.add(bVer);

		add(panel);
		setVisible(true);
	}

	public void mostrarEstadisticas() {
		System.out.println("Estadísticas de Inscripciones");

		for (Certificacion c : Sistema.listaCertificaciones) {
			int contador = 0;

			for (Estudiante e : Sistema.listaEstudiantes) {
				ArrayList<Registro> registros = e.getListaRegistrosE();
				for (Registro r : registros) {
					if (r.getIdCertificacion().equals(c.getId())) {
						contador++;
					}
				}
			}

			System.out.println("Certificación: " + c.getNombre() + " (" + c.getId() + ")");
			System.out.println("-Total inscritos: " + contador);
		}
		System.out.println("");
	}
}