package com.godric.sync_utils.reentrantlock;

import com.godric.SleepUtils;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Godric
 * @date 2020/1/16 17:22
 */

public class TestReentrantLockTry {

    Lock lock = new ReentrantLock();

    void m() {
        boolean flag = false;
        try {
            flag = lock.tryLock(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " " + flag);
        if (flag) {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + i);
                    SleepUtils.sleepSeconds(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " didn't get the lock");
        }
    }

    public static void main(String[] args) {

        TestReentrantLockTry t = new TestReentrantLockTry();

        new Thread(t::m, "t1").start();
        t.m();

    }

}
