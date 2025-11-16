class OvenMonitor {
    private boolean isBusy = false;

    public synchronized void useOven(String name) throws InterruptedException {
        while (isBusy) {
            System.out.println(name + " is waiting for the oven...");
            wait();
        }
        isBusy = true;
        System.out.println(name + " is using the oven.");
    }

    public synchronized void releaseOven(String name) {
        System.out.println(name + " finished baking and leaves the oven.");
        isBusy = false;
        notify();
    }
}

class Baker implements Runnable {
    private final OvenMonitor ovenMonitor;
    private final String name;

    public Baker(OvenMonitor ovenMonitor, String name) {
        this.ovenMonitor = ovenMonitor;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(name + " is preparing the dough...");
                Thread.sleep(3000);
                ovenMonitor.useOven(name);
                Thread.sleep(7000);
                ovenMonitor.releaseOven(name);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class SharedOven {
    public static void main(String[] args) {
        OvenMonitor ovenMonitor = new OvenMonitor();

        Baker baker1 = new Baker(ovenMonitor, "Baker 1");
        Baker baker2 = new Baker(ovenMonitor, "Baker 2");

        new Thread(baker1).start();
        new Thread(baker2).start();
    }
}