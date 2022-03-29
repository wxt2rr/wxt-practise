package com.wangxt.practise.volatile_test;

public class WhileTest {

    private static boolean flag = false;

    public void test(){
        while (!flag){
        }
    }

    public void test1(){
        flag = true;
    }

    public void test2(){
        System.out.println("aaa");
    }
}
