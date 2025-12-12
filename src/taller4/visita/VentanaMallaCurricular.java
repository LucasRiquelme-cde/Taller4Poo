//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4.visita;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import taller4.modelo.*;
import taller4.Sistema;

/**
 * Ventana que visualiza la malla curricular del estudiante de forma gráfica e interactiva.
 * Organiza las asignaturas por semestre y utiliza códigos de colores (Verde, Rojo, Gris)
 * para indicar el estado académico de cada curso.
 */
public class VentanaMallaCurricular extends JFrame {
	
	Sistema s = Sistema.getInstance();
	private Estudiante estudiante;

	/**
	 * Constructor de la ventana de malla curricular.
	 * Genera dinámicamente los paneles para cada semestre (del 1 al 10) y agrega
	 * los botones correspondientes a las asignaturas.
	 * * @param e El estudiante del cual se visualizará la malla.
	 */
	public VentanaMallaCurricular(Estudiante e) {
		this.estudiante = e;
		
		setTitle("Malla Curricular - " + e.getNombre());
		setSize(800, 600); 
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);


		JPanel panelPrincipal = new JPanel(new GridLayout(0, 1, 5, 5));


		for (int i = 1; i <= 10; i++) {
			JPanel panelSemestre = crearPanelSemestre(i);
			if (panelSemestre.getComponentCount() > 0) {
				panelPrincipal.add(panelSemestre);
			}
		}

		add(panelPrincipal);
		setVisible(true);
	}

	/**
	 * Crea un panel contenedor para un semestre específico.
	 * Filtra la lista de cursos del sistema para encontrar los que corresponden al semestre dado
	 * y crea un botón para cada uno, asignando el color según su estado.
	 * * @param numeroSemestre El número del semestre a renderizar.
	 * @return JPanel configurado con los botones de las asignaturas.
	 */
	private JPanel crearPanelSemestre(int numeroSemestre) {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder("Semestre " + numeroSemestre));
		
		ArrayList<Curso> listaC = s.getListaCursos(); 
		for (Curso curso : listaC) {
			if (curso.getSemestre() == numeroSemestre) {
				
				JButton botonCurso = new JButton(curso.getNombre());
				
				String estado = obtenerEstadoCurso(curso.getnRc());
				
				if (estado.equals("Aprobada")) {
					botonCurso.setBackground(Color.GREEN);
				} else if (estado.equals("Reprobada")) {
					botonCurso.setBackground(Color.RED);
					botonCurso.setForeground(Color.WHITE); 
				} else {
					botonCurso.setBackground(Color.LIGHT_GRAY);
				}
				
				botonCurso.addActionListener(e -> mostrarDetalleCurso(curso, estado));
				
				panel.add(botonCurso);
			}
		}
		return panel;
	}

	/**
	 * Determina el estado de una asignatura para el estudiante actual.
	 * Revisa el historial de notas para ver si está aprobada (>= 4.0), reprobada o pendiente.
	 * * @param nrcCurso Código único de la asignatura.
	 * @return String con el estado ("Aprobada", "Reprobada" o "Pendiente").
	 */
	private String obtenerEstadoCurso(String nrcCurso) {
		ArrayList<Nota> notas = estudiante.getListaNotas();
		
		for (Nota n : notas) {
			if (n.getCodigoAsignatura().equals(nrcCurso)) {
				if (n.getCalificacion() >= 4.0 || n.getEstado().equalsIgnoreCase("Aprobada")) {
					return "Aprobada";
				} else {
					return "Reprobada";
				}
			}
		}
		return "Pendiente";
	}

	/**
	 * Muestra los detalles de una asignatura específica en la consola.
	 * Se activa al hacer clic en el botón de la asignatura.
	 * * @param c      Objeto Curso seleccionado.
	 * @param estado Estado actual del curso para el estudiante.
	 */
	private void mostrarDetalleCurso(Curso c, String estado) {
		System.out.println("Información de Asignatura");
		System.out.println("Nombre: " + c.getNombre());
		System.out.println("Código (NRC): " + c.getnRc());
		System.out.println("Créditos: " + c.getCreditos());
		System.out.println("Área: " + c.getArea());
		System.out.println("Prerrequisitos: " + c.getPrerrequisitos());
		System.out.println("Estado actual: " + estado);
		
		for(Nota n : estudiante.getListaNotas()) {
			if(n.getCodigoAsignatura().equals(c.getnRc())) {
				System.out.println("Nota registrada: " + n.getCalificacion());
			}
		}
		System.out.println("");
	}
}