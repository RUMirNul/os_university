package task.visitcounter;

import java.util.concurrent.atomic.LongAdder;

public class VisitCounterLongAdder {
    private final LongAdder visits = new LongAdder();

    public void addVisit() {
        visits.increment();
    }

    public long getVisits() {
        return visits.sum();
    }
}
