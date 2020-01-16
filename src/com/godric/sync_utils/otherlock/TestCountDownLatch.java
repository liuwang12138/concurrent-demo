package com.godric.sync_utils.otherlock;

import com.godric.SleepUtils;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author Godric
 * @date 2020/1/16 17:50
 */

public class TestCountDownLatch {

    static CountDownLatch countDownLatch = new CountDownLatch(5);

    public static void main(String[] args) {

        System.out.println("main start");
        Thread t1 = new Thread(()->{
            SleepUtils.sleepSeconds(1);
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " count down");
        }, "t1");
        Thread t2 = new Thread(()->{
            SleepUtils.sleepSeconds(2);
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " count down");
        }, "t2");
        Thread t3 = new Thread(()->{
            SleepUtils.sleepSeconds(3);
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " count down");
        }, "t3");
        Thread t4 = new Thread(()->{
            SleepUtils.sleepSeconds(4);
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " count down");
        }, "t4");
        Thread t5 = new Thread(()->{
            SleepUtils.sleepSeconds(5);
            countDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " count down");
        }, "t5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("I am here");
    }
}
