package ru.spbstu.lab2;

public interface I2 extends I1 {
    default void i2(){
        System.out.println("Class: " + getClass().getSimpleName() + " Method: i2");
    }
}
