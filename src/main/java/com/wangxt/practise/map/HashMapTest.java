package com.wangxt.practise.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>(1,0.75F);
        map.put("1","1");
        map.put("1","1");
    }
}
