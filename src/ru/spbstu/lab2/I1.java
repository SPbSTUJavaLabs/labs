package ru.spbstu.lab2;

interface I1 {
    default String i1() {
        return "Method: i1 Class: " + getClass().getSimpleName();
    }
}
