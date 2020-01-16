package com.godric.sync_utils.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Godric
 * @date 2020/1/7 18:49
 *
 * 手动释放 finally
 */

public class TestReentrantLock {

    ReentrantLock lock = new ReentrantLock();

//    synchronized void m() {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(Thread.currentThread().getName() + " " + i);
//        }
//    }

    void m() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        TestReentrantLock t = new TestReentrantLock();

        Thread t1 = new Thread(t::m, "t1");
        t1.start();
        t.m();
    }
}
