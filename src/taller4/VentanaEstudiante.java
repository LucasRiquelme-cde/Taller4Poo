//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

import java.awt.GridLayout;
import javax.swing.*;

public class VentanaEstudiante extends JFrame {

	private Estudiante estudianteLogueado;

	public VentanaEstudiante(Estudiante estudiante) {
		this.estudianteLogueado = estudiante;
		
		setTitle("Menú Estudiante - " + estudiante.getNombre());
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));

		JLabel lBienvenida = new JLabel("Bienvenido: " + estudiante.getNombre());
		
		JButton bPerfil = new JButton("Mi Perfil y Promedios");
		JButton bMalla = new JButton("Mi Malla Curricular");
		JButton bInscripcion = new JButton("Inscribir Certificaciones");
		JButton bProgreso = new JButton("Mi Progreso");

		bPerfil.addActionListener(e -> new VentanaPerfilEstudiante(estudianteLogueado));
		bMalla.addActionListener(e -> new VentanaMallaCurricular(estudianteLogueado)); 
		bInscripcion.addActionListener(e -> new VentanaInscripcionCertificaciones(estudianteLogueado));
		bProgreso.addActionListener(e -> new VentanaProgresoEstudiante(estudianteLogueado));

		panel.add(lBienvenida);
		panel.add(bPerfil);
		panel.add(bMalla);
		panel.add(bInscripcion);
		panel.add(bProgreso);

		add(panel);
		setVisible(true);
	}
}