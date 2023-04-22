package task.visitcounter;

import java.util.concurrent.atomic.AtomicLong;

public class VisitCounterAtomic {
    private final AtomicLong visits = new AtomicLong(0);

    public void addVisit() {
        visits.incrementAndGet();
    }

    public long getVisits() {
        return visits.get();
    }

}
