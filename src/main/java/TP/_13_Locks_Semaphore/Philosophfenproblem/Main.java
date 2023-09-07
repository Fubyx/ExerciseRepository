package TP._13_Locks_Semaphore.Philosophfenproblem;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Philosoph[] philosophs = new Philosoph[5];
        Semaphore[] semaphores = new Semaphore[5];
        for (int i = 0; i < 5; i++) {
            semaphores[i] = new Semaphore(1, true);
        }
        for (int i = 0; i < 5; i++) {
            philosophs[i] = new Philosoph(semaphores[i], semaphores[(i + 1)%5], i);
            philosophs[i].start();
        }
        for (int i = 0; i < 5; i++) {
            try {
                philosophs[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
