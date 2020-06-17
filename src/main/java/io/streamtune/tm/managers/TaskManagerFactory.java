package io.streamtune.tm.managers;

/**
 * Simple factory to generate TaskManager
 */
public final class TaskManagerFactory {
    public final static TaskManager createFixed(int size){
        return new FixedTaskManager(size);
    }

    public final static TaskManager createFifo(int size){
        return new FifoTaskManager(size);
    }

    public final static TaskManager createPriority(int size){
        return new PriorityTaskManager(size);
    }
}
