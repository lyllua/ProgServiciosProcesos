import java.util.concurrent.Semaphore;

class Barber implements Runnable {
    private final Semaphore chair;
    private final Semaphore waitingRoom;

    public Barber(Semaphore chair, Semaphore waitingRoom) {
        this.chair = chair;
        this.waitingRoom = waitingRoom;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("The barber is sleeping...");
                chair.acquire();
                System.out.println("The barber is cutting hair.");
                Thread.sleep(5000);
                System.out.println("The barber finished cutting hair.");
                waitingRoom.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Customer implements Runnable {
    private final Semaphore chair;
    private final Semaphore waitingRoom;

    public Customer(Semaphore chair, Semaphore waitingRoom) {
        this.chair = chair;
        this.waitingRoom = waitingRoom;
    }

    @Override
    public void run() {
        try {
            if (waitingRoom.tryAcquire()) {
                System.out.println(Thread.currentThread().getName() + " is waiting.");
                chair.release();
            } else {
                System.out.println(Thread.currentThread().getName() + " leaves because there is no space.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class SleepingBarber {
    public static void main(String[] args) {
        Semaphore chair = new Semaphore(0);
        Semaphore waitingRoom = new Semaphore(5);
        Barber barber = new Barber(chair, waitingRoom);
        new Thread(barber).start();

        for (int i = 0; i < 15; i++) {
            new Thread(new Customer(chair, waitingRoom), "Customer " + (i + 1)).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

