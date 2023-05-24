package task.philosophers;

public class Main {

    public static int PHILOSOPHERS_MAX = 5;

    public static void main(String[] args) throws Exception {

        Philosopher[] philosophers = new Philosopher[PHILOSOPHERS_MAX];
        Object[] forks = new Object[PHILOSOPHERS_MAX];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            if (i == philosophers.length - 1) {
                philosophers[i] = new Philosopher(rightFork, leftFork);
            } else {
                philosophers[i] = new Philosopher(leftFork, rightFork);
            }

            Thread t = new Thread(philosophers[i], "Философ " + (i + 1));
            t.start();
        }
    }
}
