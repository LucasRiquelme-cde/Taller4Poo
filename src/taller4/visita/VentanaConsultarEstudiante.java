//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4.visita;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;

import taller4.modelo.*;
import taller4.Sistema;
/**
 * Ventana que permite a un Coordinador consultar el perfil completo de un estudiante.
 * Busca por RUT y muestra en consola la información personal, historial de notas
 * y estado de las certificaciones inscritas.
 */
public class VentanaConsultarEstudiante extends JFrame {

	JTextField tRut;

	/**
	 * Constructor de la ventana de consulta.
	 * Configura la interfaz gráfica solicitando el RUT del estudiante.
	 */
	public VentanaConsultarEstudiante() {
		setTitle("Consultar Perfil de Estudiante");
		setSize(400, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

		JLabel lRut = new JLabel("Ingrese RUT del Estudiante:");
		tRut = new JTextField();
		
		JButton bBuscar = new JButton("Buscar Perfil");
		bBuscar.addActionListener(e -> buscar());

		panel.add(lRut);
		panel.add(tRut);
		
		panel.add(new JLabel(""));
		panel.add(bBuscar);
		
		panel.add(new JLabel("")); 
		panel.add(new JLabel("")); 

		add(panel);
		setVisible(true);
	}

	/**
	 * Realiza la búsqueda del estudiante en el sistema mediante su RUT.
	 * Si lo encuentra, imprime en la salida estándar (consola) todos los datos
	 * académicos y personales del alumno, así como sus certificaciones.
	 */
	public void buscar() {
		String rutBuscado = tRut.getText();
		boolean encontrado = false;

		System.out.println("Buscando Estudiante: " + rutBuscado);

		for (Estudiante e : Sistema.listaEstudiantes) {
			if (e.getRut().equals(rutBuscado)) {
				encontrado = true;
				
				System.out.println("DATOS");
				System.out.println("Nombre:   " + e.getNombre());
				System.out.println("RUT:      " + e.getRut());
				System.out.println("Carrera:  " + e.getCarrera());
				System.out.println("Semestre: " + e.getSemestre());
				System.out.println("Correo:   " + e.getCorreo());
				System.out.println("");


				System.out.println("HISTORIAL ACADÉMICO");
				ArrayList<Nota> notas = e.getListaNotas();
				if (notas.isEmpty()) {
					System.out.println("Sin notas registradas");
				} else {
					for (Nota n : notas) {
						System.out.println("Asignatura: " + n.getCodigoAsignatura() 
								+ "Nota: " + n.getCalificacion() 
								+ "Estado: " + n.getEstado());
					}
				}
				System.out.println("");

				System.out.println("CERTIFICACIONES");
				ArrayList<Registro> registros = e.getListaRegistrosE();
				if (registros.isEmpty()) {
					System.out.println("Sin certificaciones inscritas");
				} else {
					for (Registro r : registros) {
						System.out.println("Certificación: " + r.getIdCertificacion() 
								+ "Estado: " + r.getEstado() 
								+ "Progreso: " + r.getProgreso() + "%");
					}
				}
				System.out.println("");
			}
		}

		if (!encontrado) {
			System.out.println("Error: No se encontró ningún estudiante con el RUT " + rutBuscado);
		}
	}
}