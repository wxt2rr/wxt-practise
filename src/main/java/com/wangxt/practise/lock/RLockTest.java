package com.wangxt.practise.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RLockTest {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try{
                    handle();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }, "a");
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
               lock.lock();
                try{
                    handle();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }, "b");

        a.start();
        TimeUnit.SECONDS.sleep(3);
        b.start();

        System.out.println("main 结束了");
    }



    private static void handle() throws Exception{
        System.out.println(Thread.currentThread().getName() + " 拿到锁了");
        TimeUnit.SECONDS.sleep(100);
    }
}