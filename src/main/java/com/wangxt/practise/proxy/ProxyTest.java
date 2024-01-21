package com.wangxt.practise.proxy;

public class ProxyTest {
    public static void main(String[] args) {
        TakerCommon takerCommon = new TakerCommon();

        Person me = (Person) takerCommon.sendWho(new GeekTao());
        me.eat();
        me.drink();

        System.out.println("========================");

        Person xiaoming = (Person) takerCommon.sendWho(new XiaoMing());
        xiaoming.eat();
        xiaoming.drink();
    }
}
