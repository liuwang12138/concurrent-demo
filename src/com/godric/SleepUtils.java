package com.godric;

import java.util.concurrent.TimeUnit;

/**
 * @author Godric
 * @date 2020/1/16 17:23
 */

public class SleepUtils {


    public static void sleepSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleepMillionSeconds(long millionSeconds) {
        try {
            Thread.sleep(millionSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
