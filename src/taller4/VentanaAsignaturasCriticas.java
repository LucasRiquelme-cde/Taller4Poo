//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Ventana que permite el análisis de asignaturas críticas (cuellos de botella).
 * Parte del menú del Coordinador. Calcula estadísticas de reprobación y promedios
 * para identificar cursos con alto índice de fracaso.
 */
public class VentanaAsignaturasCriticas extends JFrame {

	/**
	 * Constructor de la ventana.
	 * Configura la interfaz gráfica con un botón para detonar el análisis.
	 */
	public VentanaAsignaturasCriticas() {
		setTitle("Análisis de Asignaturas Críticas");
		setSize(400, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

		JLabel lTitulo = new JLabel("Detección de Cuellos de Botella");
		JLabel lInfo = new JLabel("Presione para analizar rendimiento por asignatura.");
		
		JButton bAnalizar = new JButton("Analizar Asignaturas");
		bAnalizar.addActionListener(e -> analizar());

		panel.add(lTitulo);
		panel.add(lInfo);
		panel.add(bAnalizar);

		add(panel);
		setVisible(true);
	}

	/**
	 * Ejecuta el análisis estadístico de todas las asignaturas.
	 * Recorre la lista de cursos y estudiantes para calcular el promedio general
	 * y la cantidad de reprobados. Imprime los resultados en la consola.
	 */
	public void analizar() {
		System.out.println("Análisis de Asignaturas Críticas");


		for (Curso curso : Sistema.listaCursos) {
			
			double sumaNotas = 0;
			int cantidadNotas = 0;
			int cantidadReprobados = 0;

			for (Estudiante e : Sistema.listaEstudiantes) {
				ArrayList<Nota> notas = e.getListaNotas();
				
				for (Nota n : notas) {
					if (n.getCodigoAsignatura().equals(curso.getnRc())) {
						sumaNotas += n.getCalificacion();
						cantidadNotas++;
						
						if (n.getCalificacion() < 4.0 && n.getCalificacion() > 0) {
							cantidadReprobados++;
						}
					}
				}
			}

			
			if (cantidadNotas > 0) {
				double promedio = sumaNotas / cantidadNotas;
				
				System.out.println("Asignatura: " + curso.getNombre() + " (" + curso.getnRc() + ")");
				System.out.println("-Promedio General: " + promedio);
				System.out.println("-Total Reprobados: " + cantidadReprobados);
				
				if (promedio < 4.0) {
					System.out.println("ESTADO: CRÍTICA");
				} else {
					System.out.println("ESTADO: Normal");
				}
				System.out.println("");
			}
		}
	}
}