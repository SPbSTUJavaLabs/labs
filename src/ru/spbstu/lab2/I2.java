package ru.spbstu.lab2;

interface I2 extends I1 {
    default String i2() {
        return "Method: i2 Class: " + getClass().getSimpleName();
    }
}
