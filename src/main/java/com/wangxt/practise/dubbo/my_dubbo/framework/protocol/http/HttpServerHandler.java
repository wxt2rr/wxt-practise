package com.wangxt.practise.dubbo.my_dubbo.framework.protocol.http;

import com.alibaba.fastjson.JSONObject;
import com.wangxt.practise.dubbo.my_dubbo.framework.data.DataDto;
import com.wangxt.practise.dubbo.my_dubbo.framework.regist.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

/**
 * 处理http请求
 */
public class HttpServerHandler {

    public void handle(HttpServletRequest request, HttpServletResponse response){
        try {
            // 获取参数对象
            DataDto data = JSONObject.parseObject(request.getInputStream(), DataDto.class);
            // 根据反射调用方法
            String interfaceName = data.getInterfaceName();
            Class aClass = LocalRegister.get(interfaceName + data.getVersion());

            // 获取到对应方法
            Method method = aClass.getMethod(data.getMethodName(), data.getParamsType());

            // 执行方法
            Object invoke = method.invoke(aClass.newInstance(), data.getParamValue());

            // 返回结果
            IOUtils.write(JSONObject.toJSONString(invoke).getBytes(StandardCharsets.UTF_8), response.getOutputStream());
        } catch (IOException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
