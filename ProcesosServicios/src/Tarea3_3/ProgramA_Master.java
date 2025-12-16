package Tarea3_3;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ProgramA_Master {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5555);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        Scanner scanner = new Scanner(System.in);
        StringBuilder buffer = new StringBuilder();

        while (true) {
            String line = scanner.nextLine();

            if (line.equals("ENVIAR")) {
                output.print(buffer.toString());
                output.flush();
                buffer.setLength(0);
            } else if (line.equals("FIN")) {
                output.print(buffer.toString());
                output.println("FIN");
                break;
            } else {
                buffer.append(line).append("\n");
            }
        }

        socket.close();
    }
}
