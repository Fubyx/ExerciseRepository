package TP._13_Locks_Semaphore.PhilosophersProblem;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Fork fork1 = new Fork();
        Fork fork2 = new Fork();
        Fork fork3 = new Fork();
        Fork fork4 = new Fork();
        Fork fork5 = new Fork();

        ReentrantLock collectionLock = new ReentrantLock();
        Integer eat = 0;
        Double sum = 0.0;

        Philosopher philosopher1 = new Philosopher(fork1, fork2, collectionLock, "Philosopher1", sum, eat);
        Philosopher philosopher2 = new Philosopher(fork2, fork3, collectionLock, "Philosopher2", sum, eat);
        Philosopher philosopher3 = new Philosopher(fork3, fork4, collectionLock, "Philosopher3", sum, eat);
        Philosopher philosopher4 = new Philosopher(fork4, fork5, collectionLock, "Philosopher4", sum, eat);
        Philosopher philosopher5 = new Philosopher(fork5, fork1, collectionLock, "Philosopher5", sum, eat);

        philosopher1.start();
        philosopher2.start();
        philosopher3.start();
        philosopher4.start();
        philosopher5.start();

        philosopher1.join();
        philosopher2.join();
        philosopher3.join();
        philosopher4.join();
        philosopher5.join();
        System.out.println("Average waiting time = " + sum/eat + " seconds");
    }
}
