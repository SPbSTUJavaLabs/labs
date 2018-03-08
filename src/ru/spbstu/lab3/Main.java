package ru.spbstu.lab3;

import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            System.err.println("You must input 1 positive number");
            System.exit(1);
        }
        int iterations = 0;
        try {
            iterations = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("param mus be positive integer");
            System.exit(1);
        }
        work(iterations, System.out::println);
    }

    public static Displayable work(int iterations, Displayable display) {
        Functions functions = new Functions(display);
        Functions.count = 0;
        Thread thread = new ThreadTask(functions, iterations);
        Thread thread1 = new Thread(new RunnableTask(functions, iterations));
        thread.start();
        thread1.start();
        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return functions.getResult();
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
    private Displayable display;

    Functions(Displayable display) {
        this.display = display;
    }

    Displayable getResult() {
        return display;
    }

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
        display.print(Thread.currentThread() + " count = " + count);
    }


}