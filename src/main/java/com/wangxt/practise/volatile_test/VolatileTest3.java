package com.wangxt.practise.volatile_test;

public class VolatileTest3 {
    private static int a = 0;

    public static void main(String[] args) throws Exception{
        new Thread(() -> {
            while(a == 0){
            }

        }).start();

        Thread.sleep(2000L);

        new Thread(()->{
            a = 1;
        }).start();
    }
}
