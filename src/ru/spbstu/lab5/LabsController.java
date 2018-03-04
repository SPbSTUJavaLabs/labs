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

    static String lab3(int iterations) {
        StringStream stringStream = new StringStream();
        ru.spbstu.lab3.Main.work(iterations, stringStream);
        return stringStream.toString();
    }

    static String lab4(int students, int teachers) {
        StringStream stringStream = new StringStream();
        ru.spbstu.lab4.Teacher.setOutput(stringStream);
        ru.spbstu.lab4.Main.work(students, teachers);
        return stringStream.toString();
    }

}

class StringStream implements ru.spbstu.lab3.Displayable {
    String string;

    @Override
    public void print(String string) {
        this.string += string + "\n";
    }

    public String toString() {
        return string;
    }
}
