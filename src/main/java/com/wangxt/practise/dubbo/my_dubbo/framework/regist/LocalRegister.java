package com.wangxt.practise.dubbo.my_dubbo.framework.regist;

import java.util.HashMap;
import java.util.Map;

public class LocalRegister {

    private static Map<String,Class> map = new HashMap<>();

    public static void regist(String name, Class c){
        map.put(name, c);
    }

    public static Class get(String name){
        return map.get(name);
    }
}
