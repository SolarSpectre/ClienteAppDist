package cliente.clase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Cliente extends JFrame{
    public Cliente(String IP, int puerto){
        super("GUI Cliente");
        JTextField num1, num2;
        JLabel label1,label2,resultadoLabel;
        JButton botonSuma, botonResta, botonMultiplicar , botonDividir;
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        label1 = new JLabel("Número 1:");
        label1.setBounds(20, 20, 80, 25);
        panel.add(label1);

        num1 = new JTextField();
        num1.setBounds(110, 20, 150, 25);
        panel.add(num1);

        label2 = new JLabel("Número 2:");
        label2.setBounds(20, 60, 80, 25);
        panel.add(label2);

        num2 = new JTextField();
        num2.setBounds(110, 60, 150, 25);
        panel.add(num2);

        resultadoLabel = new JLabel("Resultado: ");
        resultadoLabel.setBounds(20, 180, 260, 25);
        panel.add(resultadoLabel);


        botonSuma = new JButton("+");
        botonSuma.setBounds(20, 110, 50, 50);
        panel.add(botonSuma);
        botonSuma.addActionListener(e -> {
            String resultado = enviarNumeros(IP, puerto, Integer.parseInt(num1.getText()), Integer.parseInt(num2.getText()), "+");
            num1.setText("");
            num2.setText("");
            resultadoLabel.setText("Resultado: " + resultado);
        });

        botonResta = new JButton("-");
        botonResta.setBounds(90, 110, 50, 50);
        panel.add(botonResta);
        botonResta.addActionListener(e -> {
            String resultado = enviarNumeros(IP, puerto, Integer.parseInt(num1.getText()), Integer.parseInt(num2.getText()), "-");
            num1.setText("");
            num2.setText("");
            resultadoLabel.setText("Resultado: " + resultado);
        });

        botonMultiplicar = new JButton("*");
        botonMultiplicar.setBounds(160, 110, 50, 50);
        panel.add(botonMultiplicar);
        botonMultiplicar.addActionListener(e -> {
            String resultado = enviarNumeros(IP, puerto, Integer.parseInt(num1.getText()), Integer.parseInt(num2.getText()), "*");
            num1.setText("");
            num2.setText("");
            resultadoLabel.setText("Resultado: " + resultado);
        });

        botonDividir = new JButton("/");
        botonDividir.setBounds(230, 110, 50, 50);
        panel.add(botonDividir);
        botonDividir.addActionListener(e -> {
            String resultado = enviarNumeros(IP, puerto, Integer.parseInt(num1.getText()), Integer.parseInt(num2.getText()), "/");
            num1.setText("");
            num2.setText("");
            resultadoLabel.setText("Resultado: " + resultado);
        });

        add(panel);
        setVisible(true);
    }

    public String enviarNumeros(String IP, int puerto, int num1, int num2, String operador) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress direccionIPServer = InetAddress.getByName(IP);
            String mensajeSalida = num1 + ","+num2+","+operador;
            byte[] bufferSalida = mensajeSalida.getBytes();

            DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length,direccionIPServer,puerto);
            socket.send(paqueteSalida);

            byte[] bufferEntrada =  new byte[1024];
            DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
            socket.receive(paqueteEntrada);

            String respuestaServidor = new String(paqueteEntrada.getData(),0, paqueteEntrada.getLength());
            System.out.println("Resultado esperado del Servidor: "+respuestaServidor);
            return respuestaServidor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Sin respuesta, error";
    }
}
