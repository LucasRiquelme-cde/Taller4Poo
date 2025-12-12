//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4.Presentacion;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;

import taller4.Dominio.*;
import taller4.Sistema;
import taller4.Dominio.Certificacion;
import taller4.Dominio.Estudiante;
import taller4.Dominio.Registro;

/**
 * Ventana que muestra las estadísticas de inscripciones en las distintas líneas de certificación.
 * Permite al coordinador visualizar cuántos estudiantes están inscritos en cada certificación
 * mediante un reporte en consola.
 */
public class VentanaEstadisticasInscripciones extends JFrame {

	/**
	 * Constructor de la ventana de estadísticas.
	 * Configura la interfaz con un botón para generar el reporte.
	 */
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

	/**
	 * Genera y muestra las estadísticas de inscripción.
	 * Recorre todas las certificaciones y cuenta cuántos registros existen para cada una
	 * en la base de estudiantes, imprimiendo el total por consola.
	 */
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