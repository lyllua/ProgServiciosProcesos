package Tarea3_3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ProgramA_Sender {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();

        String message = "PING";
        byte[] sendData = message.getBytes();
        InetAddress address = InetAddress.getByName("localhost");

        DatagramPacket packetToSend = new DatagramPacket(sendData, sendData.length, address, 5555);
        socket.send(packetToSend);

        byte[] buffer = new byte[1024];
        DatagramPacket packetReceived = new DatagramPacket(buffer, buffer.length);
        socket.receive(packetReceived);

        String response = new String(packetReceived.getData(), 0, packetReceived.getLength());

        if (response.equals("OK")) {
            System.out.println("Confirmation received");
        }

        socket.close();
    }
}