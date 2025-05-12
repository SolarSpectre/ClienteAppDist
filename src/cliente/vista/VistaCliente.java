package cliente.vista;

import cliente.clase.Cliente;

import javax.swing.*;
        import java.awt.*;

public class VistaCliente extends JFrame {
    public VistaCliente() {
        setTitle("Aplicacion Cliente");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        JTextField input = new JTextField(20);
        JButton botonbuscar = new JButton("Buscar");
        topPanel.add(input);
        topPanel.add(botonbuscar);

        JPanel resultsPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        JLabel resId = new JLabel();
        JLabel resNombre = new JLabel();
        JLabel resTelefono = new JLabel();
        JLabel resCarrera = new JLabel();
        JLabel resSemestre = new JLabel();
        JLabel resGratuidad = new JLabel();
        resultsPanel.add(resId);
        resultsPanel.add(resNombre);
        resultsPanel.add(resTelefono);
        resultsPanel.add(resCarrera);
        resultsPanel.add(resSemestre);
        resultsPanel.add(resGratuidad);

        add(topPanel, BorderLayout.NORTH);
        add(resultsPanel, BorderLayout.CENTER);

        botonbuscar.addActionListener(e ->
                consultarServidor(resId, resNombre, resTelefono, resCarrera, resSemestre, resGratuidad, input));
    }


    private void consultarServidor(JLabel resId, JLabel resNombre,JLabel resTelefono, JLabel resCarrera, JLabel resSemestre, JLabel resGratuidad, JTextField input) {
        try {
            String id = input.getText();

            Cliente c = new Cliente();
            String respuesta = c.consultarEstudiante("172.31.116.75", 4005, id);

            System.out.println("Respuesta del servidor: " + respuesta);

            String[] datos = respuesta.split(",");

            if (datos.length >= 6) {
                resId.setText("ID: " + datos[0]);
                resNombre.setText("Nombre: " + datos[1]);
                resTelefono.setText("Teléfono: " + datos[2]);
                resCarrera.setText("Carrera: " + datos[3]);
                resSemestre.setText("Semestre: " + datos[4]);
                resGratuidad.setText("Gratuidad: " + datos[5]);
            } else {
                resId.setText("Respuesta incompleta del servidor.");
                resNombre.setText("");
                resTelefono.setText("");
                resCarrera.setText("");
                resSemestre.setText("");
                resGratuidad.setText("");
            }

        } catch (Exception e) {
            resId.setText("Error al recibir datos");
            resNombre.setText("");
            resTelefono.setText("");
            resCarrera.setText("");
            resSemestre.setText("");
            resGratuidad.setText("");
            e.printStackTrace();
        }
    }


}