/*
Escribe una pareja de programas A y B que transfieran mensaje entre ellos.
El programa A deberá solicitar mensajes por la entrada estándar al usuario y enviarlos al programa B que lo imprimirá por su salida estándar.
El programa A deberá agrupar todos los mensajes y enviarlo de una sola vez al programa B. Cuando el usuario teclee CYC el programa A enviará todos los mensajes al programa B y este lo imprimirá por su salida estándar y ambos (programa A y B) finalizarán.
Utiliza SocketStream para ello.
*/

package Tarea3_2;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ProgramA_SocketStream {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        StringBuilder textBuilder = new StringBuilder();

        System.out.println("Type text. Type 'CYC' to send:");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("CYC")) {
                break;
            }
            textBuilder.append(input).append("\n");
        }

        Socket socket = new Socket("localhost", 5555);
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        output.print(textBuilder.toString());
        System.out.println("Data sent. Exiting...");

        socket.close();
    }
}