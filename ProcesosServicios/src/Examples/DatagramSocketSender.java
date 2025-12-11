package Examples;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DatagramSocketSender {
    public static void main(String[] args) {
        System.out.println("Creating datagram socket");
        try (DatagramSocket datagramSocket = new DatagramSocket()) {
            String message = "Hello";
            InetAddress addrToSend = InetAddress.getByName("localhost");

            DatagramPacket datagram = new DatagramPacket(
                    message.getBytes(),
                    message.getBytes().length,
                    addrToSend,
                    5555
            );

            datagramSocket.send(datagram);
            System.out.println("Message sent");
            System.out.println("Finished");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}