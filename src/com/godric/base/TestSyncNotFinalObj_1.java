package com.godric.base;

import java.util.concurrent.TimeUnit;

/**
 * @author Godric
 * @date 2020/1/6 10:18
 */
public class TestSyncNotFinalObj_1 {
    Object o = new Object();

    void m() {
        synchronized (o) {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " running...");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        TestSyncNotFinalObj_1 t = new TestSyncNotFinalObj_1();
        new Thread(t::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t::m, "t2").start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.o = new Object();
    }

}
