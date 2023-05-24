package TP._13_Locks_Semaphore.Examples;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {
    Map<String, String> map = new HashMap<>();
    private StampedLock lock = new StampedLock();

    /*
    StampedLock also supports both read and write locks.
    However, lock acquisition methods return a stamp that is used to release a lock or to check if the lock is still valid
     */
    public void put(String key, String value) {
        long stamp = lock.writeLock();
        try {
            map.put(key, value);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public String get(String key) throws InterruptedException {
        long stamp = lock.readLock();
        try {
            return map.get(key);
        } finally {
            lock.unlockRead(stamp);
        }
    }

    /*
    Another feature provided by StampedLock is optimistic locking. Most of the time,
    read operations don't need to wait for write operation completion,
    and as a result of this, the full-fledged read lock isn't required.
    Instead, we can upgrade to read lock:
     */
    public String readWithOptimisticLock(String key) {
        long stamp = lock.tryOptimisticRead();
        String value = map.get(key);

        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                return map.get(key);
            } finally {
                lock.unlock(stamp);
            }
        }
        return value;
    }
}