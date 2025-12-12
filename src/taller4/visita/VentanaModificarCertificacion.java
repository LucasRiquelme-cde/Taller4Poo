//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4.visita;

import java.awt.GridLayout;
import javax.swing.*;

import taller4.modelo.*;
import taller4.Sistema;

/**
 * Ventana que permite a los coordinadores modificar la información de una línea de certificación existente.
 * Facilita la búsqueda por ID y la edición de campos como nombre, descripción, requisitos y validez.
 */
public class VentanaModificarCertificacion extends JFrame {
	
	JTextField tId, tNombre, tDescripcion, tRequisitos, tValidez;

	/**
	 * Constructor de la ventana de modificación.
	 * Configura el diseño de cuadrícula (GridLayout) e inicializa los campos de texto
	 * y botones necesarios para la gestión.
	 */
	public VentanaModificarCertificacion() {
		setTitle("Modificar Certificación");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); 

		JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));

		JLabel lId = new JLabel("Ingrese ID a buscar:");
		tId = new JTextField();
		
		JButton bBuscar = new JButton("Buscar");
		bBuscar.addActionListener(e -> buscar());

		JLabel lNombre = new JLabel("Nombre:");
		tNombre = new JTextField();

		JLabel lDescripcion = new JLabel("Descripción:");
		tDescripcion = new JTextField();

		JLabel lRequisitos = new JLabel("Requisitos:");
		tRequisitos = new JTextField();
		
		JLabel lValidez = new JLabel("Validez:");
		tValidez = new JTextField();

		JButton bGuardar = new JButton("Guardar Cambios");
		bGuardar.addActionListener(e -> guardar());


		panel.add(lId);
		panel.add(tId);
		
		panel.add(bBuscar);
		
		panel.add(lNombre);
		panel.add(tNombre);
		panel.add(new JLabel("")); 
		
		panel.add(lDescripcion);
		panel.add(tDescripcion);
		panel.add(new JLabel(""));
		
		panel.add(lRequisitos);
		panel.add(tRequisitos);
		panel.add(new JLabel(""));
		
		panel.add(lValidez);
		panel.add(tValidez);
		
		panel.add(new JLabel("")); 
		panel.add(bGuardar);

		add(panel);
		setVisible(true);
	}

	/**
	 * Busca una certificación en la lista del sistema utilizando el ID ingresado.
	 * Si la encuentra, rellena los campos de texto con la información actual para su edición.
	 */
	public void buscar() {
		String id = tId.getText();
		for (Certificacion c : Sistema.listaCertificaciones) {
			if (c.getId().equals(id)) {
				tNombre.setText(c.getNombre());
				tDescripcion.setText(c.getDescripcion());
				tRequisitos.setText(c.getRequisitos());
				tValidez.setText(c.getValidez());
				System.out.println("Certificación encontrada: " + c.getNombre());
			}
		}
	}

	/**
	 * Guarda los cambios realizados en los campos de texto sobre el objeto Certificación correspondiente.
	 * Actualiza los atributos nombre, descripción, requisitos y validez en memoria.
	 */
	public void guardar() {
		String id = tId.getText();
		for (Certificacion c : Sistema.listaCertificaciones) {
			if (c.getId().equals(id)) {
				c.setNombre(tNombre.getText());
				c.setDescripcion(tDescripcion.getText());
				c.setRequisitos(tRequisitos.getText());
				c.setValidez(tValidez.getText());
				System.out.println("Cambios guardados exitosamente.");
			}
		}
	}
}