package com.wangxt.practise.volatile_test;

public class VolatileTest {

    public static boolean flag = false;

    public static void main(String[] args) throws Exception {

        Thread aFalse = new Thread(() -> {
            while (!flag) {
            }
        });

        aFalse.start();

        Thread.sleep(2000L);

        new Thread(() -> {
            flag = true;
        }).start();

        Thread.sleep(2000L);


    }
}
