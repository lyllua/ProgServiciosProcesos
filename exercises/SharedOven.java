import java.util.concurrent.Semaphore;

class Baker implements Runnable {
    private final Semaphore oven;
    private final String name;

    public Baker(Semaphore oven, String name) {
        this.oven = oven;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Preparing dough
                System.out.println(name + " is preparing the dough...");
                Thread.sleep(3000); // 3 seconds to prepare

                // Try to bake
                System.out.println(name + " is waiting for the oven...");
                oven.acquire(); // wait for oven to be free

                System.out.println(name + " is using the oven.");
                Thread.sleep(7000); // 7 seconds to bake

                System.out.println(name + " finished baking and leaves the oven.");
                oven.release(); // free the oven
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class SharedOven {
    public static void main(String[] args) {
        Semaphore oven = new Semaphore(1);

        Baker baker1 = new Baker(oven, "Baker 1");
        Baker baker2 = new Baker(oven, "Baker 2");

        new Thread(baker1).start();
        new Thread(baker2).start();
    }
}
