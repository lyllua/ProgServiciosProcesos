import java.util.concurrent.Semaphore;

class Child implements Runnable {
    private final Semaphore swing;
    private final String name;

    public Child(Semaphore swing, String name) {
        this.swing = swing;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " is waiting for their turn.");
            swing.acquire();

            System.out.println(name + " starts using the swing.");
            Thread.sleep(5000); 

            System.out.println(name + " finished swinging and leaves.");
            swing.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Playground {
    public static void main(String[] args) {
        Semaphore swing = new Semaphore(1);

        for (int i = 1; i <= 5; i++) {
            new Thread(new Child(swing, "Child " + i)).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
