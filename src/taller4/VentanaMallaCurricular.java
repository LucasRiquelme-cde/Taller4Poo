package taller4;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class VentanaMallaCurricular extends JFrame {
	Sistema s = Sistema.getInstance();
	private Estudiante estudiante;

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