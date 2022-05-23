package com.wangxt.practise;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Tets {
    @Test
    public void t(){
        int iii = romanToInt("III");
        System.out.println(iii);
    }

    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        for(int i=s.length() - 1;i>0;i--){
            if(i == s.length() - 1){
                result = map.get(s.charAt(i));
            }else{
                int t;
                // 如果左边数比右边大，则按位相加
                if((t = map.get(s.charAt(i))) >= map.get(s.charAt(i+1))){
                    result = result + t;
                }else{
                    // 如果左边数比右边小，则减去左边的数
                    result = result - t;
                }


            }

        }

        return result;
    }
}
