package com.wangxt.practise.proxy;

/**
 * 我
 */
public class GeekTao implements Person {

    @Override
    public void eat() {
        System.out.println("我自己吃饭");
    }

    @Override
    public void drink() {
        System.out.println("我自己喝");
    }
}
