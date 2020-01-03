package com.godric.base;

import com.sun.security.jgss.GSSUtil;

/**
 * @author Godric
 * @date 2020/1/3 17:32
 *
 * Thread States:
 *      1. NEW - Not Start
 *      2. RUNNABLE
 *            Ready
 *            Running
 *      3. BLOCKED          Waiting Execute Synchronized Code
 *      4. WAITING          wait(), join(), park()
 *      5. TIMED_WAITING    Waiting With Timed Out
 *      6. Terminated       Completed
 *
 */

public class TestThreadState {
    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for(int i=0; i<10; i++) {
                System.out.println("==============" + i);
                sleep(50);
            }
        });
        System.out.println(t.getState());
        t.start();


        new Thread(()-> {
            for(;;) {
                Thread.State state = t.getState();
                System.out.println(state);
                if (state.equals(Thread.State.TERMINATED)) {
                    break;
                }
            }
        }).start();

    }

    private static void sleep(long millionSeconds) {
        try {
            Thread.sleep(millionSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
