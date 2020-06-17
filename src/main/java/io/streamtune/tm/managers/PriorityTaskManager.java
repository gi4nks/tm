package io.streamtune.tm.managers;


import io.streamtune.tm.processes.Process;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * The Priority task manager uses a ArrayList to handle the Priority requested behavior.
 * The add method simply implements the logic requested.
 */
public class PriorityTaskManager extends AbstractTaskManager {
    public PriorityTaskManager(int size) {
        super();
        this.maxSize = size;
        this.processes = new ArrayList<>(size);
    }


    /*
    A new customer is asking something different again,
    every call to the add() method, when the max size is
    reached, should result into an evaluation: if the new
    process passed in the add() call has a higher priority
    compared to any of the existing one, we remove the
    lowest priority that is the oldest, otherwise we skip it
     */

    @Override
    public synchronized void add(Process process) {
        if (processes.size() < maxSize) {
            processes.add(process);
            System.out.println("Added: " + process.toString());
        } else {
            Process pro = processes.stream()
                    .filter(p -> p.getPriority().compareTo(process.getPriority())<0)
                    .sorted(Comparator.comparing(Process::getPriority).thenComparing(Process::getCreated))
                    .findFirst()
                    .orElse(null);

            if (pro != null) {
                pro.kill();

                System.out.println("Killed: " + pro.toString());
                this.purge();

                processes.add(process);
                System.out.println("Added: " + process.toString());
            } else {
                System.out.printf("Too many process running. Process %s not added\n", process.getID());
            };

        }
    }
}


/*

 */