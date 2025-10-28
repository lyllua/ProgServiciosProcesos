import java.util.Random;

class TriangleThread extends Thread {
    private int base;
    private int height;

    public TriangleThread(int base, int height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public void run() {
        double area = (base * height) / 2.0;
        System.out.println("Thread " + Thread.currentThread().getId() +
                " → Base: " + base + ", Height: " + height + ", Area: " + area);
    }

    public static void main(String[] args) {
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            int base = rand.nextInt(30) + 1;
            int height = rand.nextInt(30) + 1;
            new TriangleThread(base, height).start();
        }
    }
}
