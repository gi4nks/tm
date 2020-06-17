package io.streamtune.tm.managers;

import io.streamtune.tm.processes.ProcessFactory;
import io.streamtune.tm.processes.Process;
import io.streamtune.tm.processes.ProcessPriorityEnum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FixedTaskManagerTest {

    @Test
    void add_1() throws InterruptedException {
        TaskManager tm = TaskManagerFactory.createFixed(10);

        Process p1 = ProcessFactory.getLowPriorityProcess();
        Process p2 = ProcessFactory.getMediumPriorityProcess();
        Process p3 = ProcessFactory.getHighPriorityProcess();
        Process p4 = ProcessFactory.getLowPriorityProcess();
        Process p5 = ProcessFactory.getMediumPriorityProcess();
        Process p6 = ProcessFactory.getHighPriorityProcess();
        Process p7 = ProcessFactory.getLowPriorityProcess();
        Process p8 = ProcessFactory.getMediumPriorityProcess();
        Process p9 = ProcessFactory.getHighPriorityProcess();
        Process p10 = ProcessFactory.getLowPriorityProcess();
        Process p11 = ProcessFactory.getMediumPriorityProcess();
        Process p12 = ProcessFactory.getHighPriorityProcess();

        Thread t1 = new Thread() {
            public void run(){
                tm.add(p1);
                tm.add(p2);
                tm.add(p3);
            }
        };

        Thread t2 = new Thread() {
            public void run(){
                tm.add(p4);
                tm.add(p5);
                tm.add(p6);
            }
        };

        Thread t3 = new Thread() {
            public void run(){
                tm.add(p7);
                tm.add(p8);
                tm.add(p9);
            }
        };

        Thread t4 = new Thread() {
            public void run(){
                tm.add(p10);
                tm.add(p11);
                tm.add(p12);
            }
        };

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();


        assertTrue(tm.listById().size()==10);
        tm.listById().stream().forEach(p -> assertTrue(p.isAlive()));
    }


    @Test
    void add_2() throws InterruptedException {
        TaskManager tm = TaskManagerFactory.createFixed(10);

        Process p1 = ProcessFactory.getLowPriorityProcess();
        Process p2 = ProcessFactory.getMediumPriorityProcess();
        Process p3 = ProcessFactory.getHighPriorityProcess();
        Process p4 = ProcessFactory.getLowPriorityProcess();
        Process p5 = ProcessFactory.getMediumPriorityProcess();
        Process p6 = ProcessFactory.getHighPriorityProcess();
        Process p7 = ProcessFactory.getLowPriorityProcess();
        Process p8 = ProcessFactory.getMediumPriorityProcess();
        Process p9 = ProcessFactory.getHighPriorityProcess();
        Process p10 = ProcessFactory.getLowPriorityProcess();
        Process p11 = ProcessFactory.getMediumPriorityProcess();
        Process p12 = ProcessFactory.getHighPriorityProcess();

        Thread t1 = new Thread() {
            public void run(){
                tm.add(p1);
                tm.add(p2);
                tm.add(p3);
                tm.add(p4);
                tm.add(p5);
            }
        };

        Thread t2 = new Thread() {
            public void run(){
                tm.add(p6);
                tm.add(p7);
                tm.add(p8);
                tm.add(p9);
                tm.add(p10);
            }
        };

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        assertTrue(tm.listById().size()==10);
        tm.listById().stream().forEach(p -> assertTrue(p.isAlive()));

        Thread t3 = new Thread() {
            public void run(){
                tm.kill(p1.getPID(), p1.getPriority());
                tm.kill(p2.getPID(), p2.getPriority());

                tm.add(p11);
                tm.add(p12);
            }
        };

        Thread t4 = new Thread() {
            public void run(){
                tm.kill(p2.getPID(), p2.getPriority());

                tm.add(p11);
                tm.add(p12);
            }
        };

        t3.start();
        t4.start();

        t3.join();
        t4.join();


        assertTrue(tm.listById().stream().filter(p -> p.getPID() == p2.getPID()).count()==0);
        tm.listById().stream().forEach(p -> assertTrue(p.isAlive()));
        assertTrue(tm.listById().stream().filter(p -> p.isAlive()).count()==10);
    }

    @Test
    void add_3() throws InterruptedException {
        TaskManager tm = TaskManagerFactory.createFixed(10);

        Process p1 = ProcessFactory.getLowPriorityProcess();
        Process p2 = ProcessFactory.getMediumPriorityProcess();

        Thread t1 = new Thread() {
            public void run(){
                tm.add(p1);
                tm.add(p2);
                tm.add(p1);
                tm.add(p2);
                tm.add(p1);
            }
        };

        Thread t2 = new Thread() {
            public void run(){
                tm.add(p1);
                tm.add(p2);
                tm.add(p1);
            }
        };

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        List<Process> list = Arrays.asList(new Process[]{p1, p2});

        assertTrue(tm.listById().containsAll(list));
        assertTrue(tm.listById().stream().filter(p -> p.isAlive()).count()==2);
    }

    @Test
    void kill_1() throws InterruptedException {
        TaskManager tm = TaskManagerFactory.createFixed(10);

        Process p1 = ProcessFactory.getLowPriorityProcess();
        Process p2 = ProcessFactory.getMediumPriorityProcess();
        Process p3 = ProcessFactory.getHighPriorityProcess();
        Process p4 = ProcessFactory.getLowPriorityProcess();
        Process p5 = ProcessFactory.getMediumPriorityProcess();
        Process p6 = ProcessFactory.getHighPriorityProcess();
        Process p7 = ProcessFactory.getLowPriorityProcess();
        Process p8 = ProcessFactory.getMediumPriorityProcess();
        Process p9 = ProcessFactory.getHighPriorityProcess();
        Process p10 = ProcessFactory.getLowPriorityProcess();
        Process p11 = ProcessFactory.getMediumPriorityProcess();
        Process p12 = ProcessFactory.getHighPriorityProcess();
        Process p13 = ProcessFactory.getLowPriorityProcess();
        Process p14 = ProcessFactory.getMediumPriorityProcess();
        Process p15 = ProcessFactory.getHighPriorityProcess();

        Thread t1 = new Thread() {
            public void run(){
                tm.add(p1);
                tm.add(p2);
                tm.add(p3);
                tm.add(p4);
                tm.add(p5);
                tm.add(p6);
                tm.add(p7);
                tm.add(p8);
                tm.add(p9);
                tm.add(p10);
            }
        };

        Thread t2 = new Thread() {
            public void run(){
                tm.kill(p4.getPID(), p4.getPriority());
                tm.add(p11);
                tm.add(p13);
                tm.add(p14);
            }
        };

        Thread t3 = new Thread() {
            public void run(){
                tm.kill(p7.getPID(), p7.getPriority());
                tm.add(p12);
                tm.add(p15);
            }
        };

        t1.start();
        t1.join();
        t2.start();
        t3.start();

        t2.join();
        t3.join();

        assertTrue(tm.listById().size()==10);
        ArrayList<Process> expected = new ArrayList<>();
        expected.add(p1);
        expected.add(p2);
        expected.add(p3);
        expected.add(p5);
        expected.add(p6);
        expected.add(p8);
        expected.add(p9);
        expected.add(p10);
        //expected.add(p11);
        //expected.add(p12);

        assertTrue(tm.listById().containsAll(expected));
    }

    @Test
    void kill_2() throws InterruptedException {
        TaskManager tm = TaskManagerFactory.createFixed(10);

        assertTrue(tm.listById().size()==0);
        tm.kill(123, ProcessPriorityEnum.LOW);
        assertTrue(tm.listById().size()==0);
    }

    @Test
    void killAll_1() throws InterruptedException {
        TaskManager tm = TaskManagerFactory.createFixed(10);

        Process p1 = ProcessFactory.getLowPriorityProcess();
        Process p2 = ProcessFactory.getMediumPriorityProcess();
        Process p3 = ProcessFactory.getHighPriorityProcess();
        Process p4 = ProcessFactory.getLowPriorityProcess();
        Process p5 = ProcessFactory.getMediumPriorityProcess();
        Process p6 = ProcessFactory.getHighPriorityProcess();
        Process p7 = ProcessFactory.getLowPriorityProcess();
        Process p8 = ProcessFactory.getMediumPriorityProcess();
        Process p9 = ProcessFactory.getHighPriorityProcess();
        Process p10 = ProcessFactory.getLowPriorityProcess();
        Process p11 = ProcessFactory.getMediumPriorityProcess();
        Process p12 = ProcessFactory.getHighPriorityProcess();
        Process p13 = ProcessFactory.getLowPriorityProcess();
        Process p14 = ProcessFactory.getMediumPriorityProcess();
        Process p15 = ProcessFactory.getHighPriorityProcess();

        Thread t1 = new Thread() {
            public void run(){
                tm.add(p1);
                tm.add(p2);
                tm.add(p3);
                tm.add(p4);
                tm.add(p5);
                tm.add(p6);
                tm.add(p7);
                tm.add(p8);
                tm.add(p9);
                tm.add(p10);
            }
        };

        Thread t2 = new Thread() {
            public void run(){
                tm.killAll();
            }
        };

        t1.start();
        t1.join();
        t2.start();
        t2.join();

        assertTrue(tm.listById().size()==0);
    }

    @Test
    void killGroup_1() throws InterruptedException {
        TaskManager tm = TaskManagerFactory.createFixed(10);

        Process p1 = ProcessFactory.getLowPriorityProcess();
        Process p2 = ProcessFactory.getMediumPriorityProcess();
        Process p3 = ProcessFactory.getHighPriorityProcess();
        Process p4 = ProcessFactory.getLowPriorityProcess();
        Process p5 = ProcessFactory.getMediumPriorityProcess();
        Process p6 = ProcessFactory.getHighPriorityProcess();
        Process p7 = ProcessFactory.getLowPriorityProcess();
        Process p8 = ProcessFactory.getMediumPriorityProcess();
        Process p9 = ProcessFactory.getHighPriorityProcess();
        Process p10 = ProcessFactory.getLowPriorityProcess();
        Process p11 = ProcessFactory.getMediumPriorityProcess();
        Process p12 = ProcessFactory.getHighPriorityProcess();
        Process p13 = ProcessFactory.getLowPriorityProcess();
        Process p14 = ProcessFactory.getMediumPriorityProcess();
        Process p15 = ProcessFactory.getHighPriorityProcess();

        Thread t1 = new Thread() {
            public void run(){
                tm.add(p1);
                tm.add(p2);
                tm.add(p3);
                tm.add(p4);
                tm.add(p5);
                tm.add(p6);
                tm.add(p7);
                tm.add(p8);
                tm.add(p9);
                tm.add(p10);
            }
        };

        Thread t2 = new Thread() {
            public void run(){
                tm.killGroup(ProcessPriorityEnum.LOW);
            }
        };

        t1.start();
        t1.join();
        t2.start();
        t2.join();

        assertTrue(tm.listById().size()==6);
        assertTrue(tm.listById().stream().filter(p -> p.getPriority()!=ProcessPriorityEnum.LOW).count()==6);
    }

    @Test
    void listById_1() throws InterruptedException {
        TaskManager tm = TaskManagerFactory.createFixed(10);

        Process p1 = ProcessFactory.getLowPriorityProcess();
        Process p2 = ProcessFactory.getMediumPriorityProcess();
        Process p3 = ProcessFactory.getHighPriorityProcess();
        Process p4 = ProcessFactory.getLowPriorityProcess();
        Process p5 = ProcessFactory.getMediumPriorityProcess();
        Process p6 = ProcessFactory.getHighPriorityProcess();
        Process p7 = ProcessFactory.getLowPriorityProcess();
        Process p8 = ProcessFactory.getMediumPriorityProcess();
        Process p9 = ProcessFactory.getHighPriorityProcess();
        Process p10 = ProcessFactory.getLowPriorityProcess();
        Process p11 = ProcessFactory.getMediumPriorityProcess();
        Process p12 = ProcessFactory.getHighPriorityProcess();
        Process p13 = ProcessFactory.getLowPriorityProcess();
        Process p14 = ProcessFactory.getMediumPriorityProcess();
        Process p15 = ProcessFactory.getHighPriorityProcess();

        Thread t1 = new Thread() {
            public void run() {
                try {
                    tm.add(p1);
                    Thread.sleep(10);
                    tm.add(p2);
                    Thread.sleep(10);
                    tm.add(p3);
                    Thread.sleep(10);
                    tm.add(p4);
                    Thread.sleep(10);
                    tm.add(p5);
                    Thread.sleep(10);
                    tm.add(p6);
                    Thread.sleep(10);
                    tm.add(p7);
                    Thread.sleep(10);
                    tm.add(p8);
                    Thread.sleep(10);
                    tm.add(p9);
                    Thread.sleep(10);
                    tm.add(p10);
                    Thread.sleep(10);
                    tm.add(p11);
                    Thread.sleep(10);
                    tm.add(p12);
                    Thread.sleep(10);
                    tm.add(p13);
                    Thread.sleep(10);
                    tm.add(p14);
                    Thread.sleep(10);
                    tm.add(p15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        t1.join();

        ArrayList<Process> expected = new ArrayList<>();
        expected.add(p6);
        expected.add(p7);
        expected.add(p8);
        expected.add(p9);
        expected.add(p10);
        expected.add(p1);
        expected.add(p2);
        expected.add(p3);
        expected.add(p4);
        expected.add(p5);
        //expected.add(p11);
        //expected.add(p12);

        assertTrue(tm.listById().size() == 10);
        assertTrue(tm.listById().containsAll(expected));
        tm.listById().stream().forEach(p -> assertTrue(p.isAlive()));
        assertTrue(tm.listById().stream().filter(p -> p.getPriority() == ProcessPriorityEnum.LOW).count() == 4);
        assertTrue(tm.listById().stream().filter(p -> p.getPriority() == ProcessPriorityEnum.MEDIUM).count() == 3);
        assertTrue(tm.listById().stream().filter(p -> p.getPriority() == ProcessPriorityEnum.HIGH).count() == 3);
    }
}