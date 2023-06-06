package com.wangxt.practise.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

public class OomTest1 {

    static List list = new ArrayList(); //静态的集合类，是不会被回收，因为他是类属性。
//    private String name; //全局变量。他的生命周期随着 OomTest1 的生命周期进行。

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//       while (true) {
//           list.add(new Object());
//       }
    }

    public String contatString() {
        String name = new String("abc");
        return name;
    }
}
