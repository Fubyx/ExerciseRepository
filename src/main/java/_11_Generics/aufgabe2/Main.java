package _11_Generics.aufgabe2;

import _11_Generics.aufgabe3.Rings;

public class Main {
    public static void main(String[] args) {

        Ring<Integer> ri = new Ring<Integer>(10);
        System.out.println(ri);
        int i = 0;
        while (ri.size() < ri.capacity())
            ri.add(i++);
        System.out.println(ri);
        for (i = 10; i < 15; i++)
            ri.set(i);
        System.out.println(ri);
        while (ri.size() > 0) {
            ri.remove();
            System.out.println(ri);
        }
    }
}
