package ru.spbstu.lab2;

public class A implements I1 {

    @Override
    public void i1() {

    }

    public void a1() {
        System.out.println("Class: " + getClass().getSimpleName() + " Method:"+getClass());
    }


}
