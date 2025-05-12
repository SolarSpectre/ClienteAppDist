package cliente.clase;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class Cliente {
    public String consultarEstudiante(String IP, int puerto, String id) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress direccionIpServidor = InetAddress.getByName(IP);

            String mensajeSalida = id;
            byte[] bufferSalida = mensajeSalida.getBytes();

            DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionIpServidor, puerto);
            socket.send(paqueteSalida);


            byte[] bufferEntrada = new byte[1024];
            DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
            socket.receive(paqueteEntrada);

            String respuestaServidor = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());

            socket.close();
            return respuestaServidor;

        } catch (Exception e) {
            e.printStackTrace();
            return "Errorn";
        }
    }
}