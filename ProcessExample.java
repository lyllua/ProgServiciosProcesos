import java.io.IOException;

public class ProcessExample {
    public static void main(String[] args) {
        try {
            // Run the ping command
            ProcessBuilder pb = new ProcessBuilder("ping", "-n 1 localhost");
            Process process = pb.start();

            // Wait for the process to finish
            int exitCode = process.waitFor();
            System.out.println("Process finished with exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}