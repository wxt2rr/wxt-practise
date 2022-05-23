package com.wangxt.practise.collection.list;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(10);
        list.add(2,1);

        System.out.println(list.toString());
    }
}
