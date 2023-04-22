package task.visitcounter;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        timeComparison(10000);

    }

    public static void timeComparison(int iterations) throws InterruptedException {
        long start;
        System.out.println("Без синхронизации:");
        start = System.currentTimeMillis();
        visitCounterAsync(iterations);
        System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");
        System.out.println();

        System.out.println("Без синхронизации через временную переменную:");
        start = System.currentTimeMillis();
        visitCounterFreeAsync(iterations);
        System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");
        System.out.println();

        System.out.println("Синхронизация метода:");
        start = System.currentTimeMillis();
        visitCounterMethodSync(iterations);
        System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");
        System.out.println();


        System.out.println("Блок синхронизации:");
        start = System.currentTimeMillis();
        visitCounterBlockSync(iterations);
        System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");
        System.out.println();

        System.out.println("ReentrantLock синхронизация:");
        start = System.currentTimeMillis();
        visitCounterReentrantLockSync(iterations);
        System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");
        System.out.println();


        System.out.println("AtomicLong синхронизация:");
        start = System.currentTimeMillis();
        visitCounterAtomicSync(iterations);
        System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");
        System.out.println();


        System.out.println("LongAdder синхронизация:");
        start = System.currentTimeMillis();
        visitCounterLongAdderSync(iterations);
        System.out.println("Time: " + (System.currentTimeMillis() - start) + "ms");
        System.out.println();

    }

    public static void manyIterationsTest(int testCaseCount, int iterations) throws InterruptedException {
        System.out.println("Без синхронизации:");
        for (int i = 0; i < testCaseCount; i++) {
            visitCounterAsync(iterations);
            System.out.println();
        }

        System.out.println("Без синхронизации через временную переменную:");
        for (int i = 0; i < testCaseCount; i++) {
            visitCounterFreeAsync(iterations);
            System.out.println();
        }

        System.out.println("Синхронизация метода:");
        for (int i = 0; i < testCaseCount; i++) {
            visitCounterMethodSync(iterations);
            System.out.println();
        }

        System.out.println("Блок синхронизации:");
        for (int i = 0; i < testCaseCount; i++) {
            visitCounterBlockSync(iterations);
            System.out.println();
        }

        System.out.println("ReentrantLock синхронизация:");
        for (int i = 0; i < testCaseCount; i++) {
            visitCounterReentrantLockSync(iterations);
            System.out.println();
        }

        System.out.println("AtomicLong синхронизация:");
        for (int i = 0; i < testCaseCount; i++) {
            visitCounterAtomicSync(iterations);
            System.out.println();
        }

        System.out.println("LongAdder синхронизация:");
        for (int i = 0; i < testCaseCount; i++) {
            visitCounterLongAdderSync(iterations);
            System.out.println();
        }
    }

    public static void visitCounterAsync(int count) throws InterruptedException {
        VisitCounter visitCounter = new VisitCounter();
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            Thread t = new Thread(() -> {
                visitCounter.addVisit();
                countDownLatch.countDown();
            });
            t.start();
        }

        countDownLatch.await();
        System.out.println("Должно быть: " + count + " Получилось: " + visitCounter.getVisits());
    }

    public static void visitCounterFreeAsync(int count) throws InterruptedException {
        VisitCounter visitCounter = new VisitCounter();
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            Thread t = new Thread(() -> {
                visitCounter.addVisitFree();
                countDownLatch.countDown();
            });
            t.start();
        }

        countDownLatch.await();
        System.out.println("Должно быть: " + count + " Получилось: " + visitCounter.getVisits());
    }

    public static void visitCounterMethodSync(int count) throws InterruptedException {
        VisitCounter visitCounter = new VisitCounter();
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            Thread t = new Thread(() -> {
                visitCounter.addVisitMethodSync();
                countDownLatch.countDown();
            });
            t.start();
        }

        countDownLatch.await();
        System.out.println("Должно быть: " + count + " Получилось: " + visitCounter.getVisits());
    }

    public static void visitCounterBlockSync(int count) throws InterruptedException {
        VisitCounter visitCounter = new VisitCounter();
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            Thread t = new Thread(() -> {
                visitCounter.addVisitBlockSync();
                countDownLatch.countDown();
            });
            t.start();
        }

        countDownLatch.await();
        System.out.println("Должно быть: " + count + " Получилось: " + visitCounter.getVisits());
    }

    public static void visitCounterReentrantLockSync(int count) throws InterruptedException {
        VisitCounter visitCounter = new VisitCounter();
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            Thread t = new Thread(() -> {
                visitCounter.addVisitReentrantLockSync();
                countDownLatch.countDown();
            });
            t.start();
        }

        countDownLatch.await();
        System.out.println("Должно быть: " + count + " Получилось: " + visitCounter.getVisits());
    }


    public static void visitCounterAtomicSync(int count) throws InterruptedException {
        VisitCounterAtomic visitCounter = new VisitCounterAtomic();
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            Thread t = new Thread(() -> {
                visitCounter.addVisit();
                countDownLatch.countDown();
            });
            t.start();
        }
        countDownLatch.await();
        System.out.println("Должно быть: " + count + " Получилось: " + visitCounter.getVisits());
    }

    public static void visitCounterLongAdderSync(int count) throws InterruptedException {
        VisitCounterLongAdder visitCounter = new VisitCounterLongAdder();
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            Thread t = new Thread(() -> {
                visitCounter.addVisit();
                countDownLatch.countDown();
            });
            t.start();
        }
        countDownLatch.await();
        System.out.println("Должно быть: " + count + " Получилось: " + visitCounter.getVisits());
    }
}
