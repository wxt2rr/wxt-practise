package com.wangxt.practise.socket.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class NioBuffer {

    public static void main(String[] args) {
        /**
         *     private int position = 0;   存放的位置
         *     private int limit;     可存放的大小
         *     private int capacity   容量
         */


        // 创建一个 capacity 为 10 的buffer
        IntBuffer intBuffer = IntBuffer.allocate(10);

        // 添加两个元素
        intBuffer.put(1);
        intBuffer.put(2);
        print(intBuffer);


        /*Buffer flip = intBuffer.flip();
        print(intBuffer);
        print(flip);*/

        intBuffer.position(5);
        print(intBuffer);
    }



    private static void print(Buffer buffer){
        System.out.printf("position:%s limit:%s capacity:%s isReadOnly:%s", buffer.position(), buffer.limit(), buffer.capacity(), buffer.isReadOnly());
        System.out.println("================");
    }
}
