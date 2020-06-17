package io.streamtune.tm.managers;


import io.streamtune.tm.processes.Process;
import io.streamtune.tm.processes.ProcessPriorityEnum;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Without any specific additional information about kind of performances requested for the task manager (i.e. add process
 * or read/list or kill and so on) I decided to implement a generic List<Process> as holder for my processes.
 *
 * The abstract class has synchronized methods (specially the kills one) to allow multithreading and avoid issues.
 *
 * I used intensively java 8 stream to handle the lists.
 *
 * The purge method is used to cleanup the not alive processes from the list.
 *
 * I have generally not implemented any kind of exception. So in the edge cases the methods simply writes to the stdout
 * a generic message that shown the handled situation.
 */
public abstract class AbstractTaskManager implements TaskManager {
    protected List<Process> processes;
    protected int maxSize;

    protected Predicate<Process> isAlive = pro -> pro.isAlive();
    protected Predicate<Process> isNotAlive = pro -> !pro.isAlive();

    protected AbstractTaskManager() {

    }

    public abstract void add(Process process);

    @Override
    public List<Process> listByCreation() {
        Collections.sort(processes, Comparator.comparing(Process::getCreated));
        return processes;
    }

    @Override
    public List<Process> listByPriority() {
        Collections.sort(processes, Comparator.comparing(Process::getPriority));
        return processes;
    }

    @Override
    public List<Process> listById() {
        Collections.sort(processes, Comparator.comparing(Process::getPID));
        return processes;
    }

    @Override
    public synchronized void kill(long pid, ProcessPriorityEnum priority) {
        Process pro = processes.stream()
                .filter(p -> p.getPID() == pid && p.getPriority().equals(priority))
                .findAny()
                .orElse(null);

        if (pro != null) {
            pro.kill();
            System.out.println("Killed: " + pro.toString());
            this.purge();
        } else
            System.out.printf("Process %s - %s not found\n", pid, priority);

    }

    @Override
    public synchronized void killGroup(ProcessPriorityEnum priority) {
        processes.stream()
                .filter(p -> p.getPriority() == priority)
                .forEach(p -> p.kill());
        this.purge();

    }

    @Override
    public synchronized void killAll() {
        processes.stream().forEach(pr -> pr.kill());
        this.purge();
    }

    protected void purge() {
        processes.removeIf(isNotAlive);
    }
}
