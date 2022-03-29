package com.wangxt.practise.volatile_test;

public class VolatileTest2 implements Runnable {
    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {

        }
        flag = true;
        System.out.println("flag=" + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    static class Thread2 {
        public static void main(String[] args) {
            VolatileTest2 t1 = new VolatileTest2();

            while (true) {
                if (t1.isFlag()) {
                    System.out.println(Thread.currentThread().getName() + "===============");
                    break;
                }
            }

            new Thread(t1).start();
        }
    }
}
