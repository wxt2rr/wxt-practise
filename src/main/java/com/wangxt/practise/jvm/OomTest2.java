package com.wangxt.practise.jvm;

import java.util.concurrent.ExecutionException;

public class OomTest2 {
    // 一个数据转储一个服务
    // 接收数据，然后对数据进行转储。
    // 接收数据通过 中间件 进行监听接收（mq)

    // 读取数据库大量数据
    // 读取大文件
    // 从网络上加载大的数据包

    //解决方案
    //1 . 可以通过中间件进行数据的缓冲
    //2. 分批进行数据的获取
    //3. 增加我们的内存量（堆内存）-- 考虑： 越大的堆内存面临着时间停顿越长，垃圾量太多。
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        byte[] bytes = getData(); // 接收数据
        processData(bytes); // 进行数据的处理和转储

        // getDataFromMq(一次性取多少分batch还是一条条的)
        //processData
    }
    public static byte[] getData(){
        return new byte[10*1024*1024];
    }
    private static void processData(byte[] bytes) {
    }
}