package TP._13_Locks_Semaphore.Examples;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockWithCondition {
    /*
    Locks are like the "synchronized" block, but more flexible
    for example: notify wakes a random Thread that was waiting in the sync block,
    while here you can enable "first come, first served" by putting "true" the Constructor of the lock.
    now when a Thread finished (used lock.unlock) the longest waiting Thread gets access to the critical region.
    There are also some other uses like with the ReadWriteLock and StampedLock


    Semaphore:
    Semaphore(int num)
    Semaphore(int num, boolean how)
    Here, num specifies the initial permit count. Thus, it specifies the number of threads that can access a shared resource at any one time.
    If it is one, then only one thread can access the resource at any one time. By default, all waiting threads are granted a permit in an undefined order.
    By setting how to true, you can ensure that waiting threads are granted a permit in the order in which they requested access.
    Methods:
    acquire();           can also be used with an int as Argument to take multiple permits at once (if available, otherwise it waits)
    release();           can also be used with an int as Argument to take multiple permits at once (if available, otherwise it waits)
    availablePermits()
    drainPermits()	     Acquires and returns all permits that are immediately available.
    tryAcquire()         similar to tryLock in ReentrantLock
     */

    Stack<String> stack = new Stack<>();
    int CAPACITY = 5;

    ReentrantLock lock = new ReentrantLock();
    Condition stackEmptyCondition = lock.newCondition();
    Condition stackFullCondition = lock.newCondition();

    public void pushToStack(String item){
        try {
            lock.lock();
            while(stack.size() == CAPACITY) {
                stackFullCondition.await();
            }
            stack.push(item);
            stackEmptyCondition.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public String popFromStack() {
        try {
            lock.lock();
            while(stack.size() == 0) {
                stackEmptyCondition.await();
            }
            return stack.pop();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            stackFullCondition.signalAll();
            lock.unlock();
        }
    }
}
