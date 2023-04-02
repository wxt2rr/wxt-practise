package com.wangxt.practise.thread.thread;

public class ThreadDaemon {
    public static void main(String[] args) {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("a run over");
            }
        },"t-a");

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("b run");
                try {
                    Thread.sleep(1000 * 10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("b run over");
            }
        }, "t-b");

        a.setDaemon(true);
        a.start();
        b.start();
    }
}
