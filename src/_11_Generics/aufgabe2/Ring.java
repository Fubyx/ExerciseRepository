package _11_Generics.aufgabe2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

class Ring <T>{
    private Integer currentIndex = 0;
    private T[] ring;
    private int capacity;
    private int amountOfObjects = 0;
    Ring(int cap) {
        capacity = cap;
    }
    public int size(){
        return amountOfObjects;
    }
    public int capacity (){
        return capacity;
    }
    public void add (T t){
        if(amountOfObjects == capacity){
            throw new IllegalArgumentException();
        }
        if(amountOfObjects == 0){
            ring = (T[]) Array.newInstance(t.getClass(), capacity);
            for(int i = 0; i < capacity; ++i){
                ring[i] = null;
            }
            ring[currentIndex] = t;
        }else{
            T[] newRing = (T[]) Array.newInstance(t.getClass(), capacity);
            for(int i = 0; i < capacity; ++i){
                newRing[i] = null;
            }
            if(currentIndex - 1 < 0){
                newRing[0] = t;
                for(int i = 1; i < capacity; ++i){
                    newRing[i] = ring[i - 1];
                }
                ring = newRing;
            }else{
                for(int i = 0; i < currentIndex; ++i){
                    newRing[i] = ring[i];
                }
                newRing[currentIndex] = t;
                for(int i = currentIndex + 1; i < capacity; ++i){
                    newRing[i] = ring[i - 1];
                }
                currentIndex++;
            }
        }
        amountOfObjects++;
    }

    public void back(){
        if(amountOfObjects == 0){
            throw new IllegalArgumentException();
        }
        if(currentIndex != 0){
            currentIndex--;
        }else{
            currentIndex = amountOfObjects - 1;
        }
    }

    public void remove (){
        if(amountOfObjects == 0){
            throw new IllegalArgumentException();
        }
        T[]newRing = (T[]) Array.newInstance(ring[0].getClass(), capacity);
        for(int i = 0; i < capacity; ++i){
            newRing[i] = null;
        }
        for(int i = 0; i < currentIndex; ++i){
            newRing[i] = ring[i];
        }
        for(int i = currentIndex; i < capacity; ++i){
            newRing[i] = ring[i + 1];
        }
    }
    public void set(T t){
        if(amountOfObjects == 0){
            throw new IllegalArgumentException();
        }
        ring[currentIndex] = t;
        if(currentIndex == amountOfObjects){
            currentIndex = 0;
        }else{
            currentIndex++;
        }
    }
    public T get(T t){
        if(amountOfObjects == 0){
            throw new IllegalArgumentException();
        }
        int index = currentIndex;
        if(currentIndex == amountOfObjects){
            currentIndex = 0;
        }else{
            currentIndex++;
        }
        return ring[index];
    }
    @Override
    public String toString(){
        String s = "[";
        for(int i = 0; i < amountOfObjects - 1; ++i){
            s = s.concat(ring[i] + ", ");
        }
        if(amountOfObjects > 0){
            s = s.concat((String) ring[amountOfObjects - 1]);
        }
        s = s.concat("]");
        return s;
    }
}