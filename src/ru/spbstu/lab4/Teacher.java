package ru.spbstu.lab4;

import java.util.ArrayList;
import java.util.Random;

/**
 * class that interrupt student's threads
 */
public class Teacher implements Runnable {
    static ArrayList<Thread> students = new ArrayList<>();

    private static Indexes indexes = new Indexes();

    private static final Object mutex = new Object();
    private Random rand = new Random();
    private static ru.spbstu.lab3.Displayable output = System.out::print;

    public Teacher() {
        indexes.add(this);
    }

    public static void setOutput(ru.spbstu.lab3.Displayable out) {
        output = out;
    }

    @Override
    public void run() {
        while (!Thread.interrupted() && !students.isEmpty()) {

            synchronized (mutex) {
                while (!indexes.canTeacherRun(this) && !students.isEmpty()) {
                    try {
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (students.isEmpty()) {
                    indexes.remove(this);
                    return;
                }
                connectWithStudent();
                mutex.notifyAll();
            }
        }
        indexes.remove(this);
    }


    private void connectWithStudent() {
        indexes.next();
        Thread student = students.get(rand.nextInt(students.size()));
        output.print("Teacher's thread " + Thread.currentThread() + " = Student's thread " + student + "\n");
        student.interrupt();
        students.remove(student);
    }


    static class Indexes {
        private ArrayList<Teacher> teachers = new ArrayList<>();
        private Teacher currentTeacher;

        boolean canTeacherRun(Teacher teacher) {
            if (!teachers.isEmpty() && (currentTeacher == null)) {
                currentTeacher = teachers.get(0);
            }

            return teacher == currentTeacher;
        }

        boolean add(Teacher teacher) {
            if (teachers.indexOf(teacher) >= 0) {
                return false;
            }
            teachers.add(teacher);
            return true;
        }

        void remove(Teacher teacher) {
            teachers.remove(teacher);
            if (teachers.isEmpty()) {
                currentTeacher = null;
            }
        }

        Teacher next() {
            if ((currentTeacher == null) && !teachers.isEmpty()) {
                currentTeacher = teachers.get(0);
                return currentTeacher;
            }
            int i = teachers.indexOf(currentTeacher);

            i = i < teachers.size() - 1 ? ++i : 0;
            currentTeacher = teachers.get(i);
            return currentTeacher;
        }
    }
}
