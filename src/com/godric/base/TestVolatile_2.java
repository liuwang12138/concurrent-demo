package com.godric.base;

/**
 * @author Godric
 * @date 2020/1/6 9:51
 *
 * volatile 引用类型（包括数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 */

public class TestVolatile_2 {

    volatile static Data data;

    private static class Data {
        int a;
        int b;

        public Data(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) {
        Thread writer = new Thread(()->{
            for (int i=0; i<1000; i++) {
                data = new Data(i, i);
            }
            System.out.println("writer over");
        });

        Thread reader = new Thread(()->{
            while (data == null) {}
            int x = data.a;
            int y = data.b;
            if (x != y) {
                System.out.printf("a = %s, b=%s%n", x, y);
            }
            System.out.println("reader over");
        });
        reader.start();
        writer.start();

        try {
            reader.join();
            writer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end");
    }

}
