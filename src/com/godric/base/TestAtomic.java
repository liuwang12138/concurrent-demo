package com.godric.base;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Godric
 * @date 2020/1/6 10:26
 */

public class TestAtomic {


    static int a = 0;
    static final Object o = new Object();

    public static void main(String[] args) {

        Thread[] threads = new Thread[50];
        for (int i=0; i<50; i++) {
            threads[i] = new Thread(()->{
                for(int j=0; j<2000; j++) {
                    synchronized (o) {
                        a++;
                    }
                }
            });
        }

        long syncStartTime = System.currentTimeMillis();
        Arrays.stream(threads).forEach(Thread::start);
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long syncEndTime = System.currentTimeMillis();

        System.out.println("sync : " + (syncEndTime-syncStartTime) + "\na : " + a);

        AtomicInteger ai = new AtomicInteger();
        Thread[] threads1 = new Thread[50];
        for (int i = 0; i < 50; i++) {
            threads1[i] = new Thread(()->{
                for(int j=0; j<2000; j++) {
                    ai.incrementAndGet();
                }
            });
        }

        long atomicStartTime = System.currentTimeMillis();
        Arrays.stream(threads1).forEach(Thread::start);
        for (Thread t : threads1) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long atomicEndTime = System.currentTimeMillis();

        System.out.println("atomic : " + (atomicEndTime-atomicStartTime) + "\natomicA : " + ai.get());


}

}
