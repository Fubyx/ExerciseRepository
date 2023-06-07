package TP._13_Locks_Semaphore.PhilosophersProblem;

import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private final ReentrantLock forkLock;
    public Fork(){
        forkLock = new ReentrantLock();
    }
    public boolean getLock(){
        if(forkLock.isHeldByCurrentThread() || forkLock.tryLock()){
            return true;
        }
        return false;
    }
    public void releaseLock(){
        try {
            forkLock.unlock();
        }catch (IllegalMonitorStateException ignored){

        }
    }
}
