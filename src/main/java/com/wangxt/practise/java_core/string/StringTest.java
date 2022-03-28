package com.wangxt.practise.java_core.string;

public class StringTest {

    public void test(){
        String str = "";
        String trim = str.trim();
    }

    public void StringTrim(){
        char[] c = new char[3];

        c[0] = '1';
        c[1] = '2';
        c[2] = '3';
    }

    public void StringTrim1(){
        char[] c = {'1','2'};
        char[] a = c;

        System.out.println(a[0]);
        System.out.println(a[1]);
    }
}
