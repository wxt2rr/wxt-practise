package com.wangxt.practise.jvm;

public class OpandStack {

    private void test1() {
        int a = 0;
        int b = 1;
        System.out.println(a + b);

        int c = 0;
        System.out.println(a + b + c);
    }


    private void test2(int i) {
        for (int x = 0; x < i; x++) {
            System.out.println(x);
        }
    }
}
