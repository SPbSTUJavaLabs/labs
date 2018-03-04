package ru.spbstu.lab2;

import java.lang.reflect.Method;

public class Main {


    public static void main(String[] args) {
        System.out.println(work());
    }

    public static String work() {
        String str = "";
        B b = new B();
        str += b.i1() + "\n";
        str += b.i2() + "\n";
        str += b.a1() + "\n";
        str += b.b1() + "\n";
        I1 i = b;
        str += "Use I1\n";
        str += i.i1() + "\n";
        str += "Use I2\n";
        I2 i2 = (I2) i;
        str += i2.i1() + "\n";
        str += i2.i1() + "\n";
        str += "Use A\n";
        A a = (A) i;
        str += a.i1() + "\n";
        str += a.a1() + "\n";
        return str;
    }

}

