package _11_Generics.aufgabe1;

import java.util.ArrayList;
import java.util.Arrays;

 class Shelf<T> {
    private final ArrayList<T> items = new ArrayList<>();
    public void put(T item) {
        items.add(item);
    }

    public void put(T item, int index) {
        items.add(index, item);
    }
    public T get(int index) {
        return items.get(index);
    }
    public void putAll(T[] items) {
        this.items.addAll(Arrays.asList(items));
    }
    public T[] getAll() {
        return (T[]) items.toArray();
    }
}
