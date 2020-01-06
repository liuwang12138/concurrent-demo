package com.godric.base;

import java.util.concurrent.TimeUnit;

/**
 * @author Godric
 * @date 2020/1/6 9:42
 *
 * volatile 引用类型（包括数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 */
public class TestVolatile_1 {

    volatile static A a = new A();

    void m() {
        System.out.println("m start");
        while(a.run) {}
        System.out.println("m end");
    }

    public static void main(String[] args) {
        TestVolatile_1 t = new TestVolatile_1();
        new Thread(t::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.run = false;
    }


}

class A {
    boolean run = true;
}
