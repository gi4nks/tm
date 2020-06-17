package io.streamtune.tm.processes;

import java.util.Date;

/**
 * The Process interface
 * Additional feature can be added here. Implemented only what has been required by the instructions
 */
public interface Process {
    String getID();
    long getPID();
    ProcessPriorityEnum getPriority();
    Date getCreated();
    boolean isAlive();
    void kill();
}
