package com.wangxt.practise.dubbo.my_dubbo.provider.service;

public class ServerServiceImpl implements ServerService{
    @Override
    public String hello() {
        return "你好，陌生人";
    }

    @Override
    public String hello(String str) {
        return "你好，" + str;
    }
}
