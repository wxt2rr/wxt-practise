package com.wangxt.practise.dubbo.my_dubbo.framework.protocol.http;

import com.alibaba.fastjson.JSONObject;
import com.wangxt.practise.dubbo.my_dubbo.framework.data.DataDto;

public class HttpClient {

    public String send(String host, int port, DataDto dataDto){
        return Util.postJson(String.format("http://%s:%s", host, port), JSONObject.toJSONString(dataDto));
    }
}
