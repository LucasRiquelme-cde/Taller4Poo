//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4.visita;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;

import taller4.modelo.*;
import taller4.servicio.*;

/**
 * Ventana que muestra el perfil académico del estudiante.
 * Muestra información personal y calcula el promedio de notas.
 * Implementa el patrón de diseño **Strategy** para permitir al estudiante alternar
 * la forma en que se calcula su promedio (Promedio General vs. Solo Aprobadas).
 */
public class VentanaPerfilEstudiante extends JFrame {

	private Estudiante estudiante;
	private EstrategiaPromedio estrategia; // Referencia a la estrategia actual
	private JLabel lPromedioValor;
	private JLabel lTipoPromedio;

	/**
	 * Constructor de la ventana de perfil.
	 * Inicializa la estrategia por defecto (Promedio Simple) y configura la interfaz
	 * con los datos del estudiante.
	 * * @param e Objeto Estudiante del cual se mostrará el perfil.
	 */
	public VentanaPerfilEstudiante(Estudiante e) {
		this.estudiante = e;
		this.estrategia = new PromedioSimple(); // Estrategia inicial
		
		setTitle("Mi Perfil Académico");
		setSize(400, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));

		panel.add(new JLabel("Nombre:"));
		panel.add(new JLabel(estudiante.getNombre()));

		panel.add(new JLabel("RUT:"));
		panel.add(new JLabel(estudiante.getRut()));

		panel.add(new JLabel("Carrera:"));
		panel.add(new JLabel(estudiante.getCarrera()));

		panel.add(new JLabel("Correo:"));
		panel.add(new JLabel(estudiante.getCorreo()));
		
		// Etiquetas dinámicas para el patrón Strategy
		lTipoPromedio = new JLabel("Promedio General:");
		panel.add(lTipoPromedio);
		
		lPromedioValor = new JLabel(String.valueOf(estrategia.calcular(estudiante.getListaNotas())));
		panel.add(lPromedioValor);
		
		JButton bCambiarEstrategia = new JButton("Cambiar Estrategia");
		bCambiarEstrategia.addActionListener(event -> cambiarEstrategia());
		panel.add(new JLabel("Modo de Cálculo:"));
		panel.add(bCambiarEstrategia);
		
		JButton bSemestral = new JButton("Ver en Consola");
		bSemestral.addActionListener(event -> calcularPromedioSemestral());
		
		panel.add(new JLabel("Detalle Semestral:"));
		panel.add(bSemestral);

		add(panel);
		setVisible(true);
	}

	/**
	 * Método que ejecuta la lógica del patrón Strategy.
	 * Alterna dinámicamente el objeto 'estrategia' entre PromedioSimple y PromedioSoloAprobados,
	 * recalcula el valor utilizando el nuevo algoritmo y actualiza la vista.
	 */
	private void cambiarEstrategia() {
		if (estrategia instanceof PromedioSimple) {
			estrategia = new PromedioSoloAprobados();
			lTipoPromedio.setText("Promedio (Solo Aprobadas):");
		} else {
			estrategia = new PromedioSimple();
			lTipoPromedio.setText("Promedio (General):");
		}
		
		// Recálculo polimórfico
		double nuevoValor = estrategia.calcular(estudiante.getListaNotas());
		lPromedioValor.setText(String.valueOf(nuevoValor));
		System.out.println("Estrategia cambiada. Nuevo cálculo realizado.");
	}

	/**
	 * Imprime en la consola el detalle de las notas por semestre.
	 * Sirve como desglose para que el estudiante verifique sus calificaciones.
	 */
	private void calcularPromedioSemestral() {
		System.out.println("Promedios por Semestre para: " + estudiante.getNombre());
		ArrayList<Nota> notas = estudiante.getListaNotas();
		
		for (Nota n : notas) {
			System.out.println("Semestre: " + n.getSemestreCursado() + "  Asignatura: " + n.getCodigoAsignatura() + "  Nota: " + n.getCalificacion());
		}
		System.out.println("");
	}
}