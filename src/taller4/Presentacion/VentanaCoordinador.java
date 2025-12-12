//Matías Collao / 22.060.152-8 / ICCI
//Lucas Riquelme / 21.943.208-9 / ICCI
package taller4.Presentacion;
import javax.swing.*;
import java.awt.*;

import taller4.Sistema;
/**
 * Ventana principal del menú para el rol de Coordinador.
 * Proporciona un panel centralizado con acceso a las funcionalidades de gestión 
 * de certificaciones, generación de certificados, estadísticas y seguimiento de estudiantes.
 */
public class VentanaCoordinador extends JFrame {

    private Sistema s; 

    /**
     * Constructor de la Ventana del Coordinador.
     * Configura las propiedades básicas de la ventana e inicializa los componentes.
     * * @param s Instancia del sistema principal (Singleton) para el acceso a datos.
     */
    public VentanaCoordinador(Sistema s) {
        this.s = s;

        setTitle("Menú Coordinador");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Componentes();
        setVisible(true);
    }

    /**
     * Inicializa y organiza los botones del menú en un diseño de cuadrícula.
     * Asigna los Event Listeners para abrir las sub-ventanas correspondientes a cada funcionalidad.
     */
    private void Componentes() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));

        JButton btnModificarCertificaciones = new JButton("Modificar Certificación");
        JButton btnGenerarCertificados = new JButton("Generar Certificados");
        JButton btnEstadisticas = new JButton("Estadísticas de Inscripciones");
        JButton btnAsignaturasCriticas = new JButton("Asignaturas Críticas");
        JButton btnConsultarEstudiante = new JButton("Consultar Estudiante");
        JButton btnValidarAvance = new JButton("Validar Avance Académico");
        JButton btnSalir = new JButton("Salir");

        panel.add(btnModificarCertificaciones);
        panel.add(btnGenerarCertificados);
        panel.add(btnEstadisticas);
        panel.add(btnAsignaturasCriticas);
        panel.add(btnConsultarEstudiante);
        panel.add(btnValidarAvance);
        panel.add(btnSalir);

        add(panel);


        btnModificarCertificaciones.addActionListener(e -> new VentanaModificarCertificacion());
        btnGenerarCertificados.addActionListener(e -> new VentanaGenerarCertificados());
        btnEstadisticas.addActionListener(e -> new VentanaEstadisticasInscripciones());
        btnAsignaturasCriticas.addActionListener(e -> new VentanaAsignaturasCriticas());
        btnConsultarEstudiante.addActionListener(e -> new VentanaConsultarEstudiante());
        btnValidarAvance.addActionListener(e -> new VentanaValidarAvance());
        btnSalir.addActionListener(e -> dispose());
        
    }
}