package com.wangxt.practise.thread.thread;

public class ThreadDemo {

    public static void main(String[] args) {
        //t1();

        System.out.println("aa");

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + ": r run");
            }
        };
        r.run();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " : run");
            }
        }).run();
    }

    private static void t1(){
        Thread thread = new Thread();

        thread.start();

        thread.run();

        thread.interrupt();

        try {
            // 可响应中断
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.checkAccess();

        thread.getState();

        thread.isAlive();

        thread.isDaemon();

        thread.isInterrupted();

        thread.setDaemon(true);

        thread.setPriority(1);

        thread.getState();

        //====================
        Object obj = new Object();

        obj.notify();

        try {
            // 可响应中断
            obj.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
