package ru.spbstu.lab2;

import java.lang.reflect.Method;

public class Main {


    public static void main(String[] args) {
        B b = new B();
        b.i1();
        b.i2();
        b.a1();
        b.b1();
        I1 i = b;
        System.out.println("Use I1");
        i.i1();
        System.out.println("Use I2");
        I2 i2 = (I2)i;
        i2.i1();
        i2.i1();
        System.out.println("Use A");
        A a = (A)i;
        a.i1();
        a.a1();
    }
}

interface I1 {
    default void i1(){
        System.out.println("Method: i1 Class: "+getClass().getSimpleName());
    }
}

interface I2 extends I1 {
   default void i2(){
       System.out.println("Method: i2 Class: "+getClass().getSimpleName());
   }
}

class A implements I1 {

    public void a1() {
        System.out.println("Method: a1 Class: "+getClass().getSimpleName());// TODO: 15.02.2018 maybe rewrite to A 
    }

   /* @Override
    public void i1() {
        System.out.println("Method: i1 Interface I1");
    }
    */
}

class B extends A implements I2 {

    public void b1() {
        System.out.println("Method: b1 Class: "+getClass().getSimpleName()); // TODO: 15.02.2018  maybe rewrite to B 
    }

    /*@Override
    public void i2() {
        System.out.println("Method: i2 Interface I2");
    }
    */
}