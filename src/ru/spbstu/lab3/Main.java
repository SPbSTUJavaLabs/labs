package ru.spbstu.lab3;

public class Main {
    static long count = 0;

    public static void main(String[] args) {
        Thread thread = new ThreadTask(10, 10);
        Thread thread1 = new Thread(new Task(1000, 10));
        thread.start();
        thread1.start();
    }

    private static synchronized void add(int ammount) {

    }


    private static class ThreadTask extends Thread {
        private Task task;

        public ThreadTask(int plussAmmount, long iterations) {
            task = new Task(plussAmmount, iterations);
        }

        @Override
        public void run() {
            task.run();
        }
    }

    private static class Task implements Runnable {
        private int pluss;
        private long iterations;

        public Task(int plussAmmount, long iterations) {
            pluss = plussAmmount;
            this.iterations = iterations;
        }

        @Override
        public void run() {
            for (long i = 0; i < iterations; i++) {
                synchronized (Task.class) {
                    count += pluss;
                    System.out.println(Thread.currentThread().getName() + " count = " + count);
                }
            }
        }
    }

}