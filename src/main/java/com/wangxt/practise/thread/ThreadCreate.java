package com.wangxt.practise.thread;

public class ThreadCreate {

    public static void main(String[] args) {
        Thread thread0 = new Thread0();
        thread0.start();

        Thread thread1 = new Thread1();
        thread1.start();

        Thread2 thread2 = new Thread2();
        new Thread(thread2).start();
    }
}

class Thread0 extends Thread{

}

class Thread1 extends Thread{
    @Override
    public void run() {
        System.out.println(" 1 execute");
    }
}

class Thread2 implements Runnable{
    @Override
    public void run() {
        System.out.println(" 2 execute ");
    }
}
