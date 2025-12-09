//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Ventana que permite al coordinador validar la consistencia académica de un estudiante.
 * Compara el progreso registrado en el sistema contra el historial real de notas 
 * para detectar discrepancias (ej: si completó los cursos pero el certificado no se activó).
 */
public class VentanaValidarAvance extends JFrame {

	JTextField tRut;

	/**
	 * Constructor de la ventana de validación.
	 * Configura la interfaz para solicitar el RUT del estudiante a auditar.
	 */
	public VentanaValidarAvance() {
		setTitle("Validar Avance Académico");
		setSize(400, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

		JLabel lRut = new JLabel("Ingrese RUT Estudiante:");
		tRut = new JTextField();
		
		JButton bValidar = new JButton("Validar Avance");
		bValidar.addActionListener(e -> validar());

		panel.add(lRut);
		panel.add(tRut);
		
		panel.add(new JLabel(""));
		panel.add(bValidar);
		
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));

		add(panel);
		setVisible(true);
	}

	/**
	 * Ejecuta el proceso de auditoría académica.
	 * 1. Busca al estudiante por RUT.
	 * 2. Recorre sus certificaciones inscritas.
	 * 3. Calcula manualmente cuántos cursos de esa certificación ha aprobado realmente según sus notas.
	 * 4. Compara este cálculo con el porcentaje de progreso almacenado en el registro.
	 * 5. Emite una alerta en consola si hay inconsistencias (ej: 100% real vs <100% registrado).
	 */
	public void validar() {
		String rut = tRut.getText();
		System.out.println("Validación de Avance para: " + rut );
		
		boolean estudianteEncontrado = false;

		for (Estudiante e : Sistema.listaEstudiantes) {
			if (e.getRut().equals(rut)) {
				estudianteEncontrado = true;
				
				ArrayList<Registro> registros = e.getListaRegistrosE();
				for (Registro r : registros) {
					
					System.out.println("Analizando Certificación: " + r.getIdCertificacion());
					
					for (Certificacion cert : Sistema.listaCertificaciones) {
						if (cert.getId().equals(r.getIdCertificacion())) {
							
							ArrayList<Curso> cursosRequeridos = cert.getListaCursos();
							int cursosAprobados = 0;
							int totalCursos = cursosRequeridos.size();
							
							for (Curso curso : cursosRequeridos) {
								boolean aprobado = false;
								
								for (Nota n : e.getListaNotas()) {
									if (n.getCodigoAsignatura().equals(curso.getnRc())) {
										if (n.getCalificacion() >= 4.0 || n.getEstado().equals("Aprobada")) {
											aprobado = true;
										}
									}
								}
								
								if (aprobado) {
									cursosAprobados++;
									System.out.println(curso.getNombre() + " Aprobado");
								} else {
									System.out.println(curso.getNombre() + " Pendiente");
								}
							}
							

							System.out.println("Progreso Real: " + cursosAprobados + " de " + totalCursos + " cursos.");
							System.out.println("Progreso Registrado en Sistema: " + r.getProgreso() + "%");
							
							if (cursosAprobados == totalCursos && r.getProgreso() < 100) {
								System.out.println("ALERTA: El estudiante completó los cursos pero el sistema no muestra 100%.");
							} else {
								System.out.println("Estado de validación: Conforme.");
							}
							System.out.println("");
						}
					}
				}
			}
		}
		
		if (!estudianteEncontrado) {
			System.out.println("Estudiante no encontrado.");
		}
	}
}