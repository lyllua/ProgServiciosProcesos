package Tarea3_2;

/*
Escribe una pareja de programas A y B que transfieran mensaje entre ellos.
El programa A deberá solicitar mensajes por la entrada estándar al usuario y enviarlos al programa B que lo imprimirá por su salida estándar.
El programa A deberá agrupar todos los mensajes y enviarlo de una sola vez al programa B. Cuando el usuario teclee CYC el programa A enviará todos los mensajes al programa B y este lo imprimirá por su salida estándar y ambos (programa A y B) finalizarán.
Utiliza SocketStream para ello.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ProgramB_SocketStream {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5555);
        Socket socket = serverSocket.accept();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        serverSocket.close();
    }
}