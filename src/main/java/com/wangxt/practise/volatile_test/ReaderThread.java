package com.wangxt.practise.volatile_test;

public class ReaderThread{
    private static boolean ready;
    private static int number;

    private static class RThread extends Thread{
        public void run(){
            while (!ready){
                System.out.println(number + " , " + ready);
            }
            System.out.println(number + " , " + ready);
        }
    }

    public static void main(String[] args) throws Exception{
        new RThread().start();

        Thread.sleep(1000);

        number = 42;
        ready = true;

        Thread.sleep(10000);
    }
}
