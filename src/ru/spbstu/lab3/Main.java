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
            functions.add1000();
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
            functions.add10();
        }
    }
}

class Functions {
    static int count = 0;
    synchronized void add10() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count += 10;
        output();
        notify();
    }

    synchronized void add1000() {
        notify();
        count += 1000;
        output();
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private synchronized void output() {
        System.out.println(Thread.currentThread() + " count = " + count);
    }
}