package ru.spbstu.lab2;

class B extends A implements I2 {

    public String b1() {
        return "Method: b1 Class: " + getClass().getSimpleName();
    }

    /*@Override
    public void i2() {
        System.out.println("Method: i2 Interface I2");
    }
    */
}
