package com.godric.base;

/**
 * @author Godric
 * @date 2020/1/3 17:07
 */

public class TestTheadMethod {
    public static void main(String[] args) {
        TestTheadMethod t = new TestTheadMethod();
//        t.testJoin();
        t.testYield();
    }

    /**
     * Sleep(): Current Thread Sleep Special Time
     */
    public void testSleep() {
        new Thread(()->{
            System.out.println("test sleep...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Yield(): Current Thread Yield Current Use Of Processor
     */
    public void testYield() {
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j=0; j<100; j++) {
                    System.out.println(Thread.currentThread().getName() + " " + j);
                    if (j%10 == 0) {
                        Thread.yield();
                    }
                }
            }, "t" + i).start();
        }
    }

    /**
     * join(): Other Thread Join To Current Thread
     */
    public void testJoin() {

        Thread t2 = new Thread(()->{
            try {
                System.out.println("t2...");
                Thread.sleep(1000);
                System.out.println("t2t2...");
                Thread.sleep(1000);
                System.out.println("t2t2t2...");
                Thread.sleep(1000);
                System.out.println("t2t2t2t2...");
                Thread.sleep(1000);
                System.out.println("t2t2t2t2t2...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t1 = new Thread(()->{
            for (int i=0; i<10; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(1000);

                    if (i == 1) {
                        t2.join();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }

}
