package com.wangxt.practise.thread.thread;

public class ThreadSleep {
    public static void main(String[] args) {
        Object obj = new Object();

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    System.out.println("a run");
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    // b会一直阻塞，表示线程a未释放 obj 锁
                    System.out.println("b run");
                }
            }
        });

        a.start();
        b.start();
    }
}
