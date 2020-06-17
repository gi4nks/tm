package io.streamtune.tm.managers;


import io.streamtune.tm.processes.Process;

import java.util.ArrayList;

/**
 * The Fixed task manager uses a ArrayList to handle the Fixed requested behavior.
 * The add method simply implements the logic requested.
 */
public class FixedTaskManager extends AbstractTaskManager {

    public FixedTaskManager(int size) {
        super();
        this.maxSize = size;
        this.processes = new ArrayList<>(size);
    }

    /*
    The task manager should have a prefixed maximum
    capacity, so it can not have more than a certain
    number of running processes within itself. This value
    is defined at build time. The add(process) method in
    TM is used for it. The default behaviour is that we can
    accept new processes till when there is capacity
    inside the Task Manager, otherwise we won’t accept
    any new process
     */

    @Override
    public synchronized void add(Process process) {
        if (processes.size() < maxSize) {
            if (!processes.contains(process)) {
                processes.add(process);
                System.out.println("Added: " + process.toString());
            } else
                System.out.printf("Process %s not added cause already existing\n", process.getID());
        } else
            System.out.printf("Too many process running. Process %s not added\n", process.getID());

    }
}
