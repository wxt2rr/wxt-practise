package com.wangxt.practise.thread.thread;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.LockSupport;

public class ObjectWaitNotify {

    public static void main(String[] args){
        Object obj = new Object();

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (obj){
                        // 等待 1000s
                        System.out.println("a wait");
                        obj.wait(0);
                    }
                    System.out.println("a 被 notify");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    try {
                        Thread.sleep(1000 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    obj.notify();
                    System.out.println("b 执行 notify");
                }
            }
        });

        BlockingDeque<Thread> queue = new LinkedBlockingDeque<>();
        queue.add(a);
        queue.add(b);

        try {
            Thread thread = queue.takeFirst();
            thread.start();
            Thread thread1 = queue.takeLast();
            thread1.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
