package Examples;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class DatagramSocketReceiver {
    public static void main(String[] args) {
        System.out.println("Creating datagram socket");
        InetSocketAddress addr = new InetSocketAddress("localhost", 5555);

        try (DatagramSocket datagramSocket = new DatagramSocket(addr)) {
            // Buffer to store the incoming data
            byte[] message = new byte[20];
            DatagramPacket datagram = new DatagramPacket(message, message.length);

            datagramSocket.receive(datagram);

            String receivedMessage = new String(datagram.getData(), 0,
                    datagram.getLength());
            System.out.println("Message received: " + receivedMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}