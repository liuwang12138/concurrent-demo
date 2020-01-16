package com.godric.sync_utils.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Godric
 * @date 2020/1/16 17:45
 */

public class TestReentrantLockFairLock {

    Lock lock = new ReentrantLock(true);

    void m() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " " + i);
//            SleepUtils.sleepMillionSeconds(500);
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        TestReentrantLockFairLock t = new TestReentrantLockFairLock();
        Thread t1 = new Thread(t::m, "t1");
        Thread t2 = new Thread(t::m, "t2");
        t1.start();
        t2.start();


    }
}
