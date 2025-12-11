public class ParallelProcesses {
    public static void main(String[] args) throws Exception {

        Process p1 = new ProcessBuilder("ping", "-n", "2", "google.com").start();
        Process p2 = new ProcessBuilder("echo", "Hello").start();
        Process p3 = new ProcessBuilder("cmd", "/c", "dir").start();

        System.out.println("All processes running in parallel...");

        p1.waitFor();
        p2.waitFor();
        p3.waitFor();

        System.out.println("All processes completed successfully");
    }
}