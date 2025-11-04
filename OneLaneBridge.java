import java.util.concurrent.Semaphore;

class Vehicle implements Runnable {
    private static final Semaphore bridge = new Semaphore(1);
    private final String name;

    public Vehicle(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " is waiting to cross.");
            bridge.acquire();
            System.out.println(name + " is crossing the bridge.");
            Thread.sleep(3000);
            System.out.println(name + " has crossed the bridge.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bridge.release();
        }
    }
}

public class OneLaneBridge {
    public static void main(String[] args) {
        Thread[] vehicles = new Thread[10];
        for (int i = 0; i < vehicles.length; i++) {
            vehicles[i] = new Thread(new Vehicle("Vehicle " + (i + 1)));
            vehicles[i].start();
        }
    }
}
