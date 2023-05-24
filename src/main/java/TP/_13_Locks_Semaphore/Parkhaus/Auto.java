package TP._13_Locks_Semaphore.Parkhaus;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Auto extends Thread {
    private Semaphore parkhaus;
    private String name;
    private Random r = new Random();

    public Auto(Semaphore parkhaus, String name) {
        this.parkhaus = parkhaus;
        this.name = name;
    }

    @Override
    public void run() {
        long sleepTime = r.nextLong(1000, 3000);
        System.out.println(name + " fährt herum für " + sleepTime + "ms");
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " betritt die Warteschlange");
        parkhaus.acquireUninterruptibly();
        sleepTime = r.nextLong(2000, 5000);
        System.out.println(name + " ist im Parkhaus eingefahren und wartet " + sleepTime + "ms");
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        parkhaus.release();
        sleepTime = r.nextLong(1000, 5000);
        System.out.println(name + " ist vom Parkhaus ausgefahren und fährt noch " + sleepTime + "ms");
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " wurde geschrottet");

    }
}
