/*
Escribe una pareja de programas (A y B) que usen sockets datagram para intercambiar un mensaje llamado token.
Al arrancarse, el programa A enviará un mensaje al B con la palabra “token”.
Cuando el B la reciba, enviará de vuelta a A un mensaje con la palabra “recibido”, y terminará.
Cuando A reciba el mensaje de B, terminará también.
*/

package Tarea3_2;

import java.net.*;

public class ProgramB_Datagram {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(5555);

        byte[] buffer = new byte[1024];
        DatagramPacket packetReceived = new DatagramPacket(buffer, buffer.length);
        socket.receive(packetReceived);

        String message = new String(packetReceived.getData(), 0, packetReceived.getLength());

        if (message.equals("token")) {
            String responseMessage = "received";
            byte[] data = responseMessage.getBytes();

            DatagramPacket packetToSend = new DatagramPacket(data, data.length, packetReceived.getAddress(), packetReceived.getPort());

            socket.send(packetToSend);
        }
        socket.close();
    }
}