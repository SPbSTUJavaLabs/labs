package ru.spbstu.lab2;

class A implements I1 {

    public String a1() {
        return "Method: a1 Class: " + getClass().getSimpleName();
    }

   /* @Override
    public void i1() {
        System.out.println("Method: i1 Interface I1");
    }
    */
}
