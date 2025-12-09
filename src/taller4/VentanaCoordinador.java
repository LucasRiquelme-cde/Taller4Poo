package taller4;
import javax.swing.*;
import java.awt.*;

public class VentanaCoordinador extends JFrame {

    private Sistema s; 

    public VentanaCoordinador(Sistema s) {
        this.s = s;

        setTitle("Menú Coordinador");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Componentes();
        setVisible(true);
    }

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