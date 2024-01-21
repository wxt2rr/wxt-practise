package com.wangxt.practise.proxy;

/**
 * 外卖员
 */
public class Taker implements Person {

    private final Person me;

    public Taker() {
        me = new GeekTao();
    }

    @Override
    public void eat() {
        // 送外卖
        this.send();
        // 吃饭
        me.eat();
    }

    private void send() {
        System.out.println("外卖员把外卖送到家");
    }

    @Override
    public void drink() {
        // 送外卖
        this.send();
        // 吃饭
        me.drink();
    }
}
