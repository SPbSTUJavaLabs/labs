package ru.spbstu.lab5;


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
        return ru.spbstu.lab2.Main.work();
    }

    static void lab3(int iterations, ru.spbstu.lab3.Displayable output) {
        ru.spbstu.lab3.Main.work(iterations, output);
    }

    static void lab4(int students, int teachers, ru.spbstu.lab3.Displayable output) {
        ru.spbstu.lab4.Teacher.setOutput(output);
        ru.spbstu.lab4.Main.work(students, teachers);
    }
}


