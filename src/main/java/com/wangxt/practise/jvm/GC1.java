package com.wangxt.practise.jvm;


public class GC1 {
    public static void main(String[] args) throws InterruptedException {
        for(int i =0; i < 10000; i++) {
            new Thread(()-> {
                Object obj = new Object();
            });
        }
        Thread.sleep(1000000);
    }
}
