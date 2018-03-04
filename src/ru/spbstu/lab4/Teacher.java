package ru.spbstu.lab4;

import java.util.ArrayList;
import java.util.Random;

/**
 * class that interrupt student's threads
 */
public class Teacher implements Runnable {
    static ArrayList<Thread> students = new ArrayList<>();
    private static int current_id = 0;
    private int id;
    private static int counter = 0;
    private static final Object mutex = new Object();
    private Random rand = new Random();

    private static ru.spbstu.lab3.Displayable output = System.out::println;

    public Teacher() {
        id = counter++;
    }

    public static void setOutput(ru.spbstu.lab3.Displayable out){
        output = out;
    }
    @Override
    public void run() {
        while (!Thread.interrupted() && !students.isEmpty()) {

            synchronized (mutex) {
                while ((id != current_id) && !students.isEmpty()) {
                    try {
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (students.isEmpty()) {
                    Thread.currentThread().interrupt();
                    return;
                }
                connectWithStudent();
                mutex.notifyAll();
            }
        }
    }

    private void connectWithStudent() {
        current_id = current_id + 1 < counter ? ++current_id : 0;
        Thread student = students.get(rand.nextInt(students.size()));
        output.print("Teacher's thread " + Thread.currentThread() + " = Student's thread %s" + student + "\n");
        student.interrupt();
        students.remove(student);
    }
}
