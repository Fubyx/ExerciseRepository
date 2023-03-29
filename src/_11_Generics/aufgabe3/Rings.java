package _11_Generics.aufgabe3;

import _11_Generics.aufgabe2.Ring;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class Rings {
    public static <T> void removeAll(T o, Ring<T> r) {
        for (int i = 0; i < r.size(); i++) {
            if (o.equals(r.get())) {
                r.back();
                r.remove();
            }
        }
    }
    public static <T> Ring<T> revert(Ring<T> r) {
        Ring<T> newRing = new Ring<>(r.capacity());
        for (int i = 0; i < r.size(); i++) {
            newRing.add(r.get());
            r.back();
            r.back();
        }
        return newRing;
    }
    public static void removeRange(Ring r, int range) {
        for (int i = 0; i < range; i++) {
            r.remove();
        }
    }
    public static <T extends Comparable> Ring<T> sort(Ring<T> r) {
        Ring<T> newRing = new Ring<>(r.capacity());
        T firstElem = r.get();
        Object[] arr = new Object[r.capacity()];
        arr[0] = firstElem;
        for (int i = 1; i < r.size(); i++) {
            arr[i] = r.get();
        }
        Arrays.sort(arr);
        for (int i = 0; i < r.size(); i++) {
            newRing.add((T)arr[i]);
        }
        while(!newRing.get().equals(firstElem));
        newRing.back();
        return newRing;
    }
    public static <T extends Comparable> T max(Ring<T> r) {
        T biggest = r.get();
        T temp;
        for (int i = 1; i < r.size(); i++) {
            temp = r.get();
            if (temp.compareTo(biggest) > 0) {
                biggest = temp;
            }
        }
        return biggest;
    }
    public static <T extends Number, G extends Number> Ring<Number> merge(Ring<T> r1, Ring<G> r2) {
        Ring<Number> newRing = new Ring<>(r1.capacity() + r2.capacity());
        //unfinished

        return newRing;
    }
}
