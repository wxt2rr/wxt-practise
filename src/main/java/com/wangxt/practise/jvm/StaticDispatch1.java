package com.wangxt.practise.jvm;

import java.io.Serializable;

public class StaticDispatch1 {
    public static void sayHello(Object arg) {
        System.out.println("hello Object");
    }

    public static void sayHello(int arg) {
        System.out.println("hello int");
    }

    public static void sayHello(long arg) {
        System.out.println("hello long");
    }

    public static void sayHello(Character arg) {
        System.out.println("hello Character");
    }

    public static void sayHello(char arg) {
        System.out.println("hello char");
    }

    public static void sayHello(char... arg) {
        System.out.println("hello char ...");
    }

    public static void sayHello(Serializable arg) {
        System.out.println("hello Serializable");
    }

    public static void main(String[] args) {
        sayHello('a');
        //初看他就是一个 char，所以选择char参数的
        //第二选择是 int，因为char类型本身就可以转化成数字类型
        //第三选择是long，因为int类型向上提升类型，到long型
        //第四选择是 hello Character，找自己的封装类型
        // 第五选择，不行我就找个序列化吧，发现 Character implements java.io.Serializable。可以找妈妈接口
        //第六选择，找祖宗 Object
        //第七选择：多参数的char

        // 因为对于我们方法的重载，肯定是首选择参数个数以及参数类型更为接近的类型。
    }
}