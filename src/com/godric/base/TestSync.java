package com.godric.base;

/**
 * @author Godric
 * @date 2020/1/3 17:59
 *
 * Please Lock A Final Object
 * If Object Is Changed, Lock Will Not Work Anymore
 *
 */

public class TestSync {

    Object o = new Object();

    public static void main(String[] args) {
        TestSync t = new TestSync();
        new Thread(t::sleepMaxTime, "t1").start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.o = new Object();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t::print, "t2").start();

//


    }

    public void sleepMaxTime() {
        synchronized (o) {
            try {
                System.out.println(Thread.currentThread().getName() + " Begin Sleep");
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void print() {
        synchronized (o) {
            for (int i = 0; i < 3; i++) {
                System.out.println("Print");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
