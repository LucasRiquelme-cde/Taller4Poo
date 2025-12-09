package taller4;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaGenerarCertificados extends JFrame {
	Sistema s = Sistema.getInstance();
	public VentanaGenerarCertificados() {
		setTitle("Generar Certificados");
		setSize(400, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

		JLabel labelTitulo = new JLabel("Generación de Documentos");
		JLabel labelInfo = new JLabel("Presione el botón para procesar certificados pendientes.");
		
		JButton bGenerar = new JButton("Generar Certificados");
		bGenerar.addActionListener(e -> generar());

		panel.add(labelTitulo);
		panel.add(labelInfo);
		panel.add(bGenerar);

		add(panel);
		setVisible(true);
	}

	public void generar() {
		System.out.println("Iniciando Generación de Certificados");
		
		ArrayList<Estudiante> listaE = s.getlistaEstudiantes();

		for (Estudiante e : listaE) {
			ArrayList<Registro> registros = e.getListaRegistrosE();
			for (Registro r : registros) {
				if (r.getEstado().equals("Completado")) {
					
					System.out.println("");
					System.out.println("CERTIFICADO DE FINALIZACIÓN");
					System.out.println("Otorgado a: " + e.getNombre());
					System.out.println("RUT: " + e.getRut());
					System.out.println("Por completar: " + r.getIdCertificacion());
					System.out.println("Firma: Universidad Católica del Mish");
					System.out.println("");
				}
			}
		}
		System.out.println("");
	}
}