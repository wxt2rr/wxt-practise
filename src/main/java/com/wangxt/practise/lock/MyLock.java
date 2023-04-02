package com.wangxt.practise.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {

    private final Sync sync;

    public MyLock(){
        sync = new Sync();
    }

    //同步器

    class Sync extends AbstractQueuedSynchronizer{
        // 尝试获取，非阻塞
        @Override
        protected boolean tryAcquire(int arg) {
            return super.tryAcquire(arg);
        }

        // 尝试释放
        @Override
        protected boolean tryRelease(int arg) {
            return super.tryRelease(arg);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return super.tryAcquireShared(arg);
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            return super.tryReleaseShared(arg);
        }

        // 是不是独占
        @Override
        protected boolean isHeldExclusively() {
            return Thread.currentThread() == getExclusiveOwnerThread();
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    /**
     * 阻塞lock
     */
    @Override
    public void lock() {

    }

    /**
     * 可中断的阻塞lock
     * @throws InterruptedException
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    /**
     * 非阻塞lock
     * @return
     */
    @Override
    public boolean tryLock() {
        return false;
    }

    /**
     * 阻塞到 time 时间后的锁
     * @param time
     * @param unit
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    /**
     * 解锁
     */
    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }


    public static void main(String[] args) {
        Lock lock = new MyLock();
        lock.lock();
    }
}
