//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Ventana encargada de la emisión de certificados de finalización.
 * Permite al coordinador generar documentos oficiales (simulados en consola)
 * para aquellos estudiantes que hayan completado exitosamente una línea de certificación.
 */
public class VentanaGenerarCertificados extends JFrame {
	
	Sistema s = Sistema.getInstance();

	/**
	 * Constructor de la ventana de generación de certificados.
	 * Configura la interfaz visual con un botón de acción para procesar las solicitudes.
	 */
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

	/**
	 * Procesa la generación masiva de certificados.
	 * Recorre la lista de todos los estudiantes y verifica sus registros.
	 * Si encuentra un registro con estado "Completado", imprime el certificado correspondiente
	 * en la salida estándar.
	 */
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