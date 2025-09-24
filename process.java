public class Process {
    public static void main(String[] args) {
        // Path to Chrome (Windows)
        String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
        // URL to open
        String url = "https://www.youtube.com/shorts/16uJ-jxcKHo";

        // Command array: first the program, then the argument (the URL)
        String[] command = { chromePath, url };

        try {
            // Start the process
            Process process = Runtime.getRuntime().exec(command);

            // Wait until Chrome finishes (not really useful for a browser, but shown for example)
            int exitCode = process.waitFor();
            System.out.println("Execution finished with code: " + exitCode);

        } catch (Exception e) {
            System.out.println("Error starting process: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
