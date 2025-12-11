package Examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class StreamSocketClient {
    public static void main(String[] args) {
        System.out.println("Creating client socket");
        try (Socket clientSocket = new Socket()) {
            System.out.println("Establishing connection");

            // To specify the IP address and port number of the server stream socket
            // to which you want to connect, the connect() method uses an object
            // of the java.net.InetSocketAddress class

            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            clientSocket.connect(addr);

            try (InputStream is = clientSocket.getInputStream();
                 OutputStream os = clientSocket.getOutputStream();
                 // Character handling streams
                 InputStreamReader isr = new InputStreamReader(is);
                 OutputStreamWriter osw = new OutputStreamWriter(os);
                 // Line-based streams
                 BufferedReader bReader = new BufferedReader(isr);
                 PrintWriter pWriter = new PrintWriter(osw)) {

                System.out.println("Sending message");
                String message = "message from the client";
                pWriter.print(message);
                pWriter.flush();
                System.out.println("Message sent");
            }

            System.out.println("Finished");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}