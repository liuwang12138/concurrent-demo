package com.godric.base;

import java.util.concurrent.TimeUnit;

/**
 * @author Godric
 * @date 2020/1/6 10:02
 *
 * 锁定某对象o，如果o的属性发生改变，不影响锁的使用
 * 但是如果o变成另外一个对象，则锁定的对象发生改变
 * 应该避免将锁定对象的引用变成另外的对象
 */
public class TestSyncNotFinalObj {
    volatile static Object o = new Object();

    public static void main(String[] args) {

        new Thread(()->{
            synchronized (o) {
                System.out.println(Thread.currentThread().getName() + " start");
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end");
            }
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        o = new Object();
        System.out.println("666");

        new Thread(()->{
            synchronized (o) {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t2").start();

    }
}
