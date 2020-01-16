package com.godric.sync_utils.reentrantlock;

import com.godric.SleepUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Godric
 * @date 2020/1/16 17:32
 */

public class TestSyncInterupt {
    Lock lock = new ReentrantLock();

    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " start");
        SleepUtils.sleepSeconds(Integer.MAX_VALUE);
        System.out.println(Thread.currentThread().getName() + " end");
    }

    public static void main(String[] args) {

        TestSyncInterupt t = new TestSyncInterupt();

        Thread t1 = new Thread(t::m, "t1");
        Thread t2 = new Thread(t::m, "t2");
        t1.start();
        SleepUtils.sleepSeconds(1);
        t2.start();
        SleepUtils.sleepSeconds(5);
        t1.interrupt();
    }
}
