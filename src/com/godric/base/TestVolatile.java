package com.godric.base;

/**
 * @author Godric
 * @date 2020/1/3 18:14
 *
 * volatile
 *      变量线程间可见
 *      禁止指令重排序
 */
public class TestVolatile {

    volatile boolean running = true;

    void m() {
        System.out.println("m start...");
        while(running) {}
        System.out.println("m end...");
    }

    public static void main(String[] args) {

        TestVolatile t = new TestVolatile();

        new Thread(t::m).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.running = false;
        System.out.println("false...");
    }
}
