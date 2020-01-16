package com.godric.sync_utils.reentrantlock;

import com.godric.SleepUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Godric
 * @date 2020/1/16 17:32
 */

public class TestReentrantLockInteruptibly {
    Lock lock = new ReentrantLock();

    void m() {
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "was interrupted!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + " end");
    }

    public static void main(String[] args) {

        TestReentrantLockInteruptibly t = new TestReentrantLockInteruptibly();

        Thread t1 = new Thread(t::m, "t1");
        Thread t2 = new Thread(t::m, "t2");
        t1.start();
        SleepUtils.sleepSeconds(1);
        t2.start();
        SleepUtils.sleepSeconds(5);
        t1.interrupt();
    }
}
