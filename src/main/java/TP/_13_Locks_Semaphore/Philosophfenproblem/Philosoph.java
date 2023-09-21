package TP._13_Locks_Semaphore.Philosophfenproblem;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Philosoph extends Thread{
    Random r = new Random();
    Semaphore leftFork;
    Semaphore rightFork;
    int index;
    public Philosoph(Semaphore leftFork, Semaphore rightFork, int i) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        index = i;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("philosoph " + index + " philosophiert");
            try {
                sleep(r.nextInt(100, 500));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("philosoph " + index + " wartet auf linke gabel");
            leftFork.acquireUninterruptibly();
            System.out.println("philosoph " + index + " nimmt linke gabel");

            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean b = rightFork.tryAcquire();
            if (b) {
                System.out.println("philosoph " + index + " nimmt rechte gabel");
            } else {
                System.out.println("philosoph " + index + " kann rechte gabel nicht nehmen und legt die linke wieder hin");
                leftFork.release();
                continue;
            }
            System.out.println("philosoph " + index + " isst");
            try {
                sleep(r.nextInt(200, 800));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            leftFork.release();
            rightFork.release();
        }
        System.out.println("philosoph " + index + " geht");
    }
}