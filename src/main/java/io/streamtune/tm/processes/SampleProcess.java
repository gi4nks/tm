package io.streamtune.tm.processes;

import java.util.Date;
import java.util.Objects;

/**
 * The SampleProcess implementation
 * CreationDate is set at object create
 * All properties are private and not settable from outside. Only getters are implemented.
 *
 * Default constructor is private to avoid any kind of generation
 * Only constructor available is the public one where pid and priority is passed
 *
 * The kill method simply sets a flag isAlive a false.
 *
 * Implemented also the equals (based on pid and priority) and toString()
 */
public class SampleProcess implements Process {
    private long PID;
    private ProcessPriorityEnum priority;
    private boolean alive;
    private Date created;

    private SampleProcess() {}

    public SampleProcess(long pid, ProcessPriorityEnum priority) {
        this.PID = pid;
        this.priority = priority;
        this.alive = true;
        this.created = new Date();
    }

    public long getPID() {
        return this.PID;
    }

    public ProcessPriorityEnum getPriority() {
        return this.priority;
    }

    public boolean isAlive() {
        return this.alive;
    }

    @Override
    public String getID() {
        return this.PID + "-" + this.priority;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void kill() {
        this.alive = false;
        System.out.printf("Process %s has been killed.\n", this.getID());
    }

    @Override
    public String toString() {
        return "ProcessImpl{" +
                "PID=" + PID +
                ", priority=" + priority +
                ", alive=" + alive +
                ", created=" + created +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SampleProcess process = (SampleProcess) o;
        return PID == process.PID &&
                priority == process.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(PID, priority);
    }
}
