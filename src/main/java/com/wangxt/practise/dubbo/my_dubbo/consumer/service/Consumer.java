package com.wangxt.practise.dubbo.my_dubbo.consumer.service;

import com.wangxt.practise.dubbo.my_dubbo.framework.data.DataDto;
import com.wangxt.practise.dubbo.my_dubbo.framework.protocol.http.HttpClient;
import com.wangxt.practise.dubbo.my_dubbo.provider.service.ServerService;

public class Consumer {

    public static void main(String[] args) {
        HttpClient client = new HttpClient();

        /*DataDto dataDto = new DataDto();
        dataDto.setInterfaceName("ServerService");
        dataDto.setMethodName("hello");
        dataDto.setVersion("1.0.0");

        String result = client.send("localhost", 8080, dataDto);
        System.out.println(result);*/

        // 通过动态代理生成代理对象
    }
}
