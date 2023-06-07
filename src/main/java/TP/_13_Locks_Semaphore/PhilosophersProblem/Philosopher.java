package TP._13_Locks_Semaphore.PhilosophersProblem;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread{
    private final Fork forkLeft;
    private final Fork forkRight;
    private final ReentrantLock collectionLock;
    private final String name;
    private Double sumOfWaitTimes;
    private Integer amountOfEatTimes;

    public Philosopher (Fork leftFork, Fork rightFork, ReentrantLock collectionLock, String name, Double sumOfWaitTimes, Integer amountOfEatTimes){
        this.forkLeft = leftFork;
        this.forkRight = rightFork;
        this.collectionLock = collectionLock;
        this.name = name;
        this.sumOfWaitTimes = sumOfWaitTimes;
        this.amountOfEatTimes = amountOfEatTimes;
    }

    @Override
    public void run(){
        Random rand = new Random();
        //while(true){
        for(int i = 0; i < 200; ++i){
            try {//Thinking
                //sleep(1000 * rand.nextInt(10));
                sleep(0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(name + " wants to eat");
            double time = System.currentTimeMillis();
            while(true) {//Eating
                //*
                try {
                    if(!collectionLock.tryLock(30, TimeUnit.SECONDS)){
                        System.err.println("-----------" + name + " starved.");
                        throw new RuntimeException();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }//*/
                if (forkLeft.getLock() && forkRight.getLock()) {
                    collectionLock.unlock();
                    try {
                        time = (System.currentTimeMillis() - time)/1000;
                        if(time > 30){
                            System.err.println("-----------" + name + " starved.");
                        }
                        System.out.println("\t" + name + " starts eating after " + time + "seconds of waiting");
                        sumOfWaitTimes += time;
                        ++amountOfEatTimes;
                        //sleep(1000 * rand.nextInt(5));
                        sleep(1000);
                        System.out.println("\t\t" +name + " is done eating");
                        System.out.println("So far the average waiting time is " + sumOfWaitTimes/amountOfEatTimes);
                        break;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }finally {
                        forkLeft.releaseLock();
                        forkRight.releaseLock();
                    }
                }
                forkLeft.releaseLock();
                forkRight.releaseLock();
                collectionLock.unlock();
            }
        }
    }
}
