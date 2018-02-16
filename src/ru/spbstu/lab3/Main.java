package ru.spbstu.lab3;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new SimpleTask(10, 10, true));
        Thread thread1 = new Thread(new SimpleTask(1000, 10, false));

        thread.start();
        thread1.start();
    }

}

/*
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
*/
/*
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

}
*/
class SimpleTask implements Runnable {

    private static long count = 0;
    private static int plus;
    private boolean isFirst;
    private long iterations;

    public SimpleTask(int plus, long iterations, boolean isFirst) {
        this.plus = plus;
        this.iterations = iterations;
        this.isFirst = isFirst;
    }

    @Override
    public void run() {
        for (long i = 0; i < iterations; i++) {
            synchronized (SimpleTask.class) {
                waitOrNotify(isFirst);
                count += plus;
                System.out.println(Thread.currentThread() + " count = " + count);
                waitOrNotify(!isFirst);
            }
        }
    }

    private synchronized void waitOrNotify(boolean isFirstA) {
        if (isFirstA) {
            notify();
        } else {
            try {
              wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}