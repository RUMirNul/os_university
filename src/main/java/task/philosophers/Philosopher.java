package task.philosophers;

public class Philosopher implements Runnable {

    private final Object leftFork;
    private final Object rightFork;

   // private long eatCount = 0;

    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 10)));
    }

    @Override
    public void run() {
        try {
            while (true) {
                doAction(System.nanoTime() + ": Думает");

                synchronized (leftFork) {
                    doAction(System.nanoTime() + ": Взял левую вилку");

                    synchronized (rightFork) {
                        doAction(System.nanoTime() + ": Взял правую вилку - ест");
                        doAction(System.nanoTime() + ": Положил правую вилку");
                    }

                    doAction(System.nanoTime() + ": Положил левую вилку - думает");

                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
