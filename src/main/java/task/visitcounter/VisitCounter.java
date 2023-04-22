package task.visitcounter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VisitCounter {
    private int visits = 0;
    private final Object lock = new Object();

    private Lock reentrantLock = new ReentrantLock();

    public void addVisit() {

//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        visits++;
    }

    public void addVisitFree() {
        int temp = visits;
        temp++;
        visits = temp;
    }
    
    public synchronized void addVisitMethodSync() {

//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        visits++;
    }

    public void addVisitBlockSync() {
        synchronized (lock) {

//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

            visits++;
        }
    }

    public void addVisitReentrantLockSync() {
        reentrantLock.lock();

//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        visits++;
        reentrantLock.unlock();
    }
    

    public int getVisits() {
        return visits;
    }
}
