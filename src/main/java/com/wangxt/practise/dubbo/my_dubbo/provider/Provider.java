package com.wangxt.practise.dubbo.my_dubbo.provider;

import com.wangxt.practise.dubbo.my_dubbo.framework.protocol.http.HttpServer;
import com.wangxt.practise.dubbo.my_dubbo.framework.regist.LocalRegister;
import com.wangxt.practise.dubbo.my_dubbo.provider.service.ServerService;
import com.wangxt.practise.dubbo.my_dubbo.provider.service.ServerServiceImpl;

public class Provider {

    public static void main(String[] args) {

        // 注册服务接口
        LocalRegister.regist("ServerService" + "1.0.0", ServerServiceImpl.class);

        // 服务提供者启动 dubbo 服务
        HttpServer server = new HttpServer();
        server.start("localhost", 8080);
    }
}
