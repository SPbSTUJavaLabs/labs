package ru.spbstu.lab4;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("You must input 2 params");
            System.exit(1);
        }
        int students = 0;
        int teachers = 0;
        try {
            students = Integer.parseInt(args[0]);
            teachers = Integer.parseInt(args[1]);
            if ((teachers < 0) || (students < 0)) {
                throw new NumberFormatException("Number must be non negative");
            }
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            System.err.println("Params must be integer");
            System.exit(1);
        }

        System.out.printf("students = %d, teachers = %d\n", students, teachers);

        ThreadGroup teachersGroup = new ThreadGroup("Teacher");
        ThreadGroup studentsGroup = new ThreadGroup("Student");

        ArrayList<Thread> studentsThreads = createArray(students, Student.class, studentsGroup);
        ArrayList<Thread> teachersThreads = createArray(teachers, Teacher.class, teachersGroup);
        Teacher.students = studentsThreads;

        for (Thread thread : teachersThreads) {
            thread.start();
        }

        for (Thread thread : studentsThreads) {
            thread.start();
        }
    }

    /**
     * @param amount    amount of creating thread
     * @param classType class that's implements Runnable
     * @param group     Thread group for creating thread
     * @return <code>ArrayList of Thread </code>
     */
    private static ArrayList<Thread> createArray(int amount, Class<? extends Runnable> classType, ThreadGroup group) {
        ArrayList<Thread> list = new ArrayList<>(amount);
        for (int i = 0; i < amount; i++) {
            try {
                Runnable runnable = classType.newInstance();
                list.add(new Thread(group, runnable, group.getName() + " - " + i));
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}

/**
 * class that interrupt student's threads
 */
class Teacher implements Runnable {
    static ArrayList<Thread> students = new ArrayList<>();
    private static int current_id = 0;
    private int id;
    private static int counter = 0;
    private static final Object mutex = new Object();
    private Random rand = new Random();

    public Teacher() {
        id = counter++;
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
        System.out.printf("Teacher's thread %s = Student's thread %s\n", Thread.currentThread(), student);
        student.interrupt();
        students.remove(student);
    }
}

/**
 * class for student run while it's not interrupt
 */
class Student implements Runnable {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) ;
    }
}




