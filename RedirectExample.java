import java.io.File;
public class RedirectExample {
    public static void main(String[] args) throws Exception {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "echo Hello World");
        pb.redirectOutput(new File("exit.txt"));
        
        Process process = pb.start();
        process.waitFor();

        System.out.println("Redirect output to exit.txt");
    }
}