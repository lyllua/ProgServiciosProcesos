class FibonacciThread extends Thread {
    private int n;

    public FibonacciThread(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        int a = 1, b = 1;
        System.out.print("Fibonacci (" + n + "): ");
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int next = a + b;
            a = b;
            b = next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        FibonacciThread thread = new FibonacciThread(10);
        thread.start();
    }
}
