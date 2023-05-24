package TP._13_Locks_Semaphore.Examples;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SynchronizedHashMapWithReadWriteLock {

    private Map<String, String> syncHashMap = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    // ...
    private Lock writeLock = lock.writeLock();
    private Lock readLock = lock.readLock();

    /*
    For both write methods, we need to surround the critical section with the write lock — only one thread can get access to it
     */
    public void put(String key, String value) {
        writeLock.lock();
        try {
            syncHashMap.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public String remove(String key) {
        writeLock.lock();
        try {
            return syncHashMap.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    /*
    For both read methods, we need to surround the critical section with the read lock. Multiple threads can get access to this section if no write operation is in progress.
     */
    public String get(String key) {
        readLock.lock();
        try {
            return syncHashMap.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public boolean containsKey(String key) {
        readLock.lock();
        try {
            return syncHashMap.containsKey(key);
        } finally {
            readLock.unlock();
        }
    }
    //...
}