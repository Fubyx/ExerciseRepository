package _11_Generics.aufgabe2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

class Ring <T>{
    private Integer currentIndex = 0;
    private T[] ring;
    private int capacity;
    private int amountOfObjects = 0;
    Ring(int cap) {
        if(cap < 0){
            throw new IllegalArgumentException();
        }
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
            ring[currentIndex] = t;
        }else{
            T[] newRing = (T[]) Array.newInstance(t.getClass(), capacity);
            if(currentIndex == 0){
                newRing[0] = t;
                for(int i = 1; i < amountOfObjects + 1; ++i){
                    newRing[i] = ring[i - 1];
                }
            }else{
                for(int i = 0; i < currentIndex; ++i){
                    newRing[i] = ring[i];
                }
                newRing[currentIndex] = t;
                for(int i = currentIndex + 1; i < capacity; ++i){
                    newRing[i] = ring[i - 1];
                }
            }
            ring = newRing;
            if(currentIndex + 1 < capacity) {
                ++currentIndex;
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
        for(int i = 0; i < currentIndex; ++i){
            newRing[i] = ring[i];
        }
        for(int i = currentIndex + 1; i < capacity; ++i){
            newRing[i - 1] = ring[i];
        }
        ring = newRing;
        --amountOfObjects;
        if(currentIndex == amountOfObjects){
            currentIndex = 0;
        }
    }
    public void set(T t){
        if(amountOfObjects == 0){
            throw new IllegalArgumentException();
        }
        ring[currentIndex] = t;
        if(currentIndex == amountOfObjects - 1){
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
            s = s.concat(String.valueOf(ring[i]));
            if(i == currentIndex){
                s = s.concat("*");
            }
            s = s.concat(", ");
        }
        if(amountOfObjects > 0){
            s = s.concat(String.valueOf(ring[amountOfObjects - 1]));
            if(amountOfObjects - 1 == currentIndex){
                s = s.concat("*");
            }
        }
        s = s.concat("]");
        return s;
    }
}