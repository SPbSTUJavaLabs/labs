package ru.spbstu.lab2;

public interface I1 {
    default void i1() {
        System.out.println("Class: " + getClass().getSimpleName() + " Method: i1");
    }
}
