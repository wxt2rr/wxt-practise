package com.wangxt.practise.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 通用外卖员
 */
public class TakerCommon implements InvocationHandler {

    private Object proxyTarget;

    public Object sendWho(Object target) {
        this.proxyTarget = target;
        return Proxy.newProxyInstance(proxyTarget.getClass().getClassLoader(), proxyTarget.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("外卖员送外卖给 " + proxyTarget.getClass().getSimpleName());
        return method.invoke(proxyTarget, args);
    }
}
