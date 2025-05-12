package cliente.vista;

import cliente.clase.Cliente;

import javax.swing.*;
        import java.awt.*;

public class VistaCliente extends JFrame {
    public VistaCliente() {
        setTitle("Aplicacion Cliente");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1, 10, 10));
        JTextField input = new JTextField();
        JLabel resId = new JLabel();
        JLabel resNombre = new JLabel();
        JLabel resTelefono = new JLabel();
        JLabel resCarrera = new JLabel();
        JLabel resSemestre = new JLabel();
        JLabel resGratuidad = new JLabel();

        JButton botonbuscar = new JButton("Buscar");


        add(input);
        add(botonbuscar);
        add(resId);
        add(resNombre);
        add(resTelefono);
        add(resCarrera);
        add(resSemestre);
        add(resGratuidad);


        botonbuscar.addActionListener(e -> consultarServidor(resId,resNombre,resTelefono,resCarrera,resSemestre,resGratuidad,input));
    }

    private void consultarServidor(JLabel resId, JLabel resNombre,JLabel resTelefono, JLabel resCarrera, JLabel resSemestre, JLabel resGratuidad, JTextField input) {
        try {
            String id = String.valueOf(input.getText());

            Cliente c = new Cliente();
            String respuesta = c.consultarEstudiante("172.31.116.65", 4005, id);
            resId.setText(respuesta);
            resNombre.setText(respuesta);
            resTelefono.setText(respuesta);
            resCarrera.setText(respuesta);
            resSemestre.setText(respuesta);
            resGratuidad.setText(respuesta);

        } catch (NumberFormatException e) {
            resId.setText("Error al recibir datos");
        }
    }

}