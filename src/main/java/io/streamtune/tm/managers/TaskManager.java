package io.streamtune.tm.managers;

import io.streamtune.tm.processes.Process;
import io.streamtune.tm.processes.ProcessPriorityEnum;

import java.util.List;

/**
 * The TaskManager interface
 *
 * Methods defined refers to the use case presented.
 */
public interface TaskManager {
    void add(Process process);

    List<Process> listByCreation();
    List<Process> listByPriority();
    List<Process> listById();

    void kill(long pid, ProcessPriorityEnum priority);
    void killGroup(ProcessPriorityEnum priority);
    void killAll();
}
