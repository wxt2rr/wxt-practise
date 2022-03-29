package com.wangxt.practise.volatile_test;

public class VolatileTest4 {
    private static int a = 0;

    public static void main(String[] args) throws Exception{
        new Thread(() -> {
            while(a == 0){
            }

        }).start();



        new Thread(()->{
            try {
                Thread.sleep(2000L);
                a = 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
