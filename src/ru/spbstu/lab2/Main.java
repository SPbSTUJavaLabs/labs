package ru.spbstu.lab2;

import java.lang.reflect.Method;

public class Main {


    public static void main(String[] args) {
        B b = new B();
        System.out.println(b.i1());
        System.out.println(b.i2());
        System.out.println(b.a1());
        System.out.println(b.b1());
        I1 i = b;
        System.out.println("Use I1");
        System.out.println(i.i1());
        System.out.println("Use I2");
        I2 i2 = (I2) i;
        System.out.println(i2.i1());
        System.out.println(i2.i1());
        System.out.println("Use A");
        A a = (A) i;
        System.out.println(a.i1());
        System.out.println(a.a1());
    }

}

