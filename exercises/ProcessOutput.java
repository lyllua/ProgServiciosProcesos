import java.io.IOException;

public class ProcessOutput {
    public static void main(String[] args) {
        try {
            Process process = new ProcessBuilder("cmd.exe", "/c", "dir").start();

            String output = new String(process.getInputStream().readAllBytes());
            System.out.println(output);

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
