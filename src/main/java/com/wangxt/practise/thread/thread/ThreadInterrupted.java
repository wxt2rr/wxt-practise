package com.wangxt.practise.thread.thread;

public class ThreadInterrupted {

    public static void main(String[] args) {
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("a run");
                try {
                    Thread.sleep(1000 * 1000);
                } catch (InterruptedException e) {
                    System.out.println("a 被中断了, sleep响应了中断");
                }
            }
        });

        Thread a1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // run方法没有响应中断，所以a1不会被b线程中断
                System.out.println("a1 run");
                for(;;){
                }
            }
        });


        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("b run");

                if(!a.isInterrupted()){
                    a.interrupt();
                    System.out.println("b 想要 中断  a");
                }

                if(!a1.isInterrupted()){
                    a1.interrupt();
                    System.out.println("b 想要 中断  a1");
                }
            }
        });


        //a.start();
        a1.start();
        b.start();
    }
}
