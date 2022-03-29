package com.wangxt.practise.volatile_test;

public class VolatileTest {

    private static boolean flag = false;

    public static void main(String[] args) throws Exception{
        new Thread(() -> {
            System.out.println("等待修改");
            while(!flag){
            }

            System.out.println("结束 , " + System.currentTimeMillis());
        }).start();

        Thread.sleep(2000L);

        new Thread(()->{
            flag = true;
            System.out.println("flag 改成了true , " + System.currentTimeMillis());
        }).start();
    }
}
