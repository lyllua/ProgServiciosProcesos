package Examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class StreamSocketServer {
    public static void main(String[] args) {
        System.out.println("Creating server socket");

        try (ServerSocket serverSocket = new ServerSocket()) {
            System.out.println("Binding");
            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);

            // assigns the socket to an address and port
            serverSocket.bind(addr);

            System.out.println("Accepting connections");

            try (Socket newSocket = serverSocket.accept();
                 InputStream is = newSocket.getInputStream();
                 OutputStream os = newSocket.getOutputStream();
                 // Character handling streams
                 InputStreamReader isr = new InputStreamReader(is);
                 OutputStreamWriter osw = new OutputStreamWriter(os);
                 // Line-based streams
                 BufferedReader br = new BufferedReader(isr);
                 PrintWriter pWriter = new PrintWriter(osw)) {

                System.out.println("Connection received");
                String message = br.readLine();
                System.out.println("Message received: " + message);
            }

            System.out.println("Closing the new socket");
            System.out.println("Closing the server socket");
            System.out.println("Finished");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}