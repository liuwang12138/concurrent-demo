package com.godric.sync_utils.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Godric
 * @date 2020/1/16 17:16
 *
 * synchronized 遇异常 自动释放
 * ReentrantLock不释放 一定要在finally里释放锁
 */

public class TestReentrantLockException {

    ReentrantLock lock = new ReentrantLock();

//    synchronized void m() {
//        for (int i = 1; i <= 10; i++) {
//            System.out.println(Thread.currentThread().getName() + " " + i);
//
//            if (i % 5 == 0) {
//                int k = i / 0;
//            }
//
//        }
//    }

    void m() {
        lock.lock();
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                if (i % 5 == 0) {
                    int k = i / 0;
                }
            }
        lock.unlock();
    }

    public static void main(String[] args) {

        TestReentrantLockException t = new TestReentrantLockException();

        Thread t1 = new Thread(t::m, "t1");
        t1.start();
        t.m();
    }

}
