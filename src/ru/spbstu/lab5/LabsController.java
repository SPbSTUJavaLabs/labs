package ru.spbstu.lab5;

import ru.spbstu.lab2.A;
import ru.spbstu.lab2.B;
import ru.spbstu.lab2.I1;
import ru.spbstu.lab2.I2;

import java.security.InvalidParameterException;

class LabsController {

    static String lab1(double lineLengths[]) {
        if (lineLengths.length != 3) {
            throw new InvalidParameterException("must be 3 params");
        }
        String str;
        str = "Params: " + lineLengths[0] + " " + lineLengths[1] + " " + lineLengths[2] + "\n";
        str += "can be triangle ?: " + ru.spbstu.lab1.Main.canBeTriangle(lineLengths) + "\n";
        return str;
    }

    static String lab2() {
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

