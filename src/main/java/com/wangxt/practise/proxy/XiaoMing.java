package com.wangxt.practise.proxy;

/**
 * 小明
 */
public class XiaoMing implements Person {

    @Override
    public void eat() {
        System.out.println("小明吃饭");
    }

    @Override
    public void drink() {
        System.out.println("小明在喝");
    }
}
