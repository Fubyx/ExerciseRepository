package TP.JUnitTesting.u0;

public class Calc {

    public Calc() {}

    public int addition(int a, int b) {
        return a + b;
    }

    public double division(double a, double b) {
        if(b > 0)
            return a / b;
        else
            return 0;
    }

    public int faculty(int x) {
        if(x > 0)
            return x*faculty(x-1);
        else
            return 1;
    }

    public boolean isPositiveValue(int x) {
        if(x > 0)
            return true;
        else
            return false;
    }

}