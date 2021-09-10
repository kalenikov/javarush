package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    private Thread thread;

    @Override
    public void run() {
        //while (true) {
		while (!Thread.currentThread().isInterrupted()) { //для этой задачи больше подходит чем while (true)
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public void start(String threadName) {
        thread = new Thread(new TaskManipulator(), threadName);
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }
}