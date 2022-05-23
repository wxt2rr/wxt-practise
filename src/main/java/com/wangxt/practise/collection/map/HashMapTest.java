package com.wangxt.practise.collection.map;

import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    public static void main(String[] args) throws Exception {
        Map<String,String> m = new HashMap<>();
        m.put("a","a");

        Field entrySet = HashMap.class.getDeclaredField("entrySet");
        entrySet.setAccessible(true);
        Object o = entrySet.get(m);
        System.out.println(o);

    }
}
