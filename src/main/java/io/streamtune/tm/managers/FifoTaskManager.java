package io.streamtune.tm.managers;


import io.streamtune.tm.processes.Process;

import java.util.LinkedList;

/**
 * The Fifo task manager uses a LinkedList to handle the Fifo requested behavior.
 * The add method simply implements the logic requested.
 */
public class FifoTaskManager extends AbstractTaskManager {

    public FifoTaskManager(int size) {
        super();
        this.maxSize = size;
        this.processes = new LinkedList<>();
    }

    /*
    A different customer wants a different behaviour:
    he’s asking to accept all new processes through the
    add() method, killing and removing from the TM list
    the oldest one (First-In, First-Out) when the max size
    is reached
     */

    @Override
    public synchronized void add(Process process) {
        if (processes.size() < maxSize) {
            processes.add(0, process);
            System.out.println("Added: " + process.toString());
        } else {
            Process last = ((LinkedList<Process>) processes).getLast();
            last.kill();
            System.out.println("Removed: " + last.toString());
            processes.add(0, process);
            System.out.println("Added: " + process.toString());
            this.purge();
        }
    }
}
