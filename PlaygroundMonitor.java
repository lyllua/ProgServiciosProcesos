class SwingMonitor {
    private boolean isBusy = false;

    public synchronized void useSwing(String name) throws InterruptedException {
        System.out.println(name + " is waiting for their turn.");

        while (isBusy) {
            wait();
        }

        isBusy = true;
        System.out.println(name + " starts using the swing.");
    }

    public synchronized void releaseSwing(String name) {
        System.out.println(name + " finished swinging and leaves.");
        isBusy = false;
        notify();
    }
}

class Child implements Runnable {
    private final SwingMonitor swingMonitor;
    private final String name;

    public Child(SwingMonitor swingMonitor, String name) {
        this.swingMonitor = swingMonitor;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            swingMonitor.useSwing(name);

            Thread.sleep(5000);

            swingMonitor.releaseSwing(name);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Playground {
    public static void main(String[] args) {
        SwingMonitor swingMonitor = new SwingMonitor();

        for (int i = 1; i <= 5; i++) {
            new Thread(new Child(swingMonitor, "Child " + i)).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
