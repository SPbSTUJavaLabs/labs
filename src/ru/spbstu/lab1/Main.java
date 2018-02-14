package ru.spbstu.lab1;

public class Main {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("You input wrong amount of arguments, it must be 3 arguments");
            System.exit(1);
        }
        double linesLengts[] = new double[3];
        try {
            for (int i = 0; i < 3; i++) {
                linesLengts[i] = Double.parseDouble(args[i]);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.err.println("All arguments must be Numbers");
            System.exit(1);
        }
        System.out.println("Input arguments:");
        for (double length : linesLengts) {
            System.out.println(length);
        }
        try {
            System.out.println("It can be triangle? " + (canBeTriangle(linesLengts) ? "Yes" : "No"));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private static boolean canBeTriangle(double lineLengts[]) {
        if (lineLengts.length != 3) {
            throw new IllegalArgumentException("Length must be equals 3");
        }
        return ((lineLengts[0] + lineLengts[1]) > lineLengts[2]) && ((lineLengts[0] + lineLengts[2]) > lineLengts[1]) &&
                ((lineLengts[1] + lineLengts[2]) > lineLengts[0]);
    }
}
