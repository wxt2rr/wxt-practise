package com.wangxt.practise.jvm;

public class StaticDispatch {
    static abstract class Human {
    }
    static class Man extends Human {
    }
    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }
    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }
    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);
        // Human 属于我们的 静态类型（外观类型）； new Man 和 new Woman 属于 实际类型（运行时类型）
        //对于方法的重载，在编译器会直接通过我们的静态类型（外观类型） 进行方法版本的确认。
    }
}