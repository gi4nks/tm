package io.streamtune.tm.processes;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple factory to generate processes (in that specific case the SampleProcess
 * implementation of the Process interface) based on the priority.
 *
 * The PIDS final static property defines a unique id (per execution) assignable per process.
 */
public final class ProcessFactory {
    private final static AtomicLong PIDS = new AtomicLong(0);

    public final static Process getLowPriorityProcess(){
        return new SampleProcess(PIDS.incrementAndGet(), ProcessPriorityEnum.LOW);
    }

    public final static Process getMediumPriorityProcess(){
        return new SampleProcess(PIDS.incrementAndGet(), ProcessPriorityEnum.MEDIUM);
    }

    public final static Process getHighPriorityProcess(){
        return new SampleProcess(PIDS.incrementAndGet(), ProcessPriorityEnum.HIGH);
    }
}
