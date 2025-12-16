package Tarea3_3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ProgramB_Receiver {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(5555);

        byte[] buffer = new byte[1024];
        DatagramPacket packetReceived = new DatagramPacket(buffer, buffer.length);
        socket.receive(packetReceived);

        String message = new String(packetReceived.getData(), 0, packetReceived.getLength());

        if (message.equals("PING")) {
            System.out.println("Ping received");

            byte[] responseData = "OK".getBytes();
            DatagramPacket packetToSend = new DatagramPacket(
                    responseData,
                    responseData.length,
                    packetReceived.getAddress(),
                    packetReceived.getPort()
            );
            socket.send(packetToSend);
        }

        socket.close();
    }
}
