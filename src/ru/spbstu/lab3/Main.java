package ru.spbstu.lab3;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Functions functions = new Functions();
        Thread thread = new ThreadTask(functions, 10);
        Thread thread1 = new Thread(new RunnableTask(functions, 10));
        thread.start();
        thread1.start();
    }

}

class RunnableTask implements Runnable {
    private int iterations;
    private Functions functions;

    public RunnableTask(Functions functions, int iterations) {
        this.iterations = iterations;
        this.functions = functions;
    }

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            functions.add(1000, true);
        }
    }
}

class ThreadTask extends Thread {
    private int iterations;
    private Functions functions;

    public ThreadTask(Functions functions, int iterations) {
        this.iterations = iterations;
        this.functions = functions;
    }

    @Override
    public void run() {
        for (int i = 0; i < iterations; i++) {
            functions.add(10, false);
        }
    }
}

class Functions {
    static int count = 0;

    synchronized void add(int amount, boolean isFirst) {
        waitOrNotify(isFirst);
        add(amount);
        waitOrNotify(!isFirst);
    }

    private synchronized void waitOrNotify(boolean isFirst) {
        if (isFirst) {
           notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void add(int amount) {
        count += amount;
        System.out.println(Thread.currentThread() + " count = " + count);
    }

}