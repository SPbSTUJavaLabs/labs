package ru.spbstu.lab2;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        System.out.println("Create class b");
        B b = new B();
        b.i1();
        b.i2();
        b.a1();
        b.b2();
        System.out.println("for I1");
        I1 i = b;
        i.i1();
        System.out.println("cast to I2");
        ((I2) i).i1();
        ((I2) i).i2();
        System.out.println("cast to A");
        ((A) i).i1();
        ((A) i).a1();
        System.out.println("cast to B");
        ((B) i).i1();
        ((B) i).i2();
        ((B) i).a1();
        ((B) i).b2();
    }

    private static <T extends I1> void executeMethods(T  t) {
        /*for (Method method : methods) {
            System.out.println(method.getName());
        }
        */
    }



}
