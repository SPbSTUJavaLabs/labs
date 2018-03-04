package ru.spbstu.lab4;

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

        work(students, teachers);
    }

    public static void work(int students, int teachers) {
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
     * @return ArrayList of Thread
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
 * class for student run while it's not interrupt
 */
class Student implements Runnable {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) ;
    }
}




