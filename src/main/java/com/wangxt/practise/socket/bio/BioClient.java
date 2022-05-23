package com.wangxt.practise.socket.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class BioClient {

    public static void main(String[] args) {
        try {
            // 创建套接字
            Socket socket = new Socket();
            // 客户端绑定端口，不绑定默认分配
            socket.bind(new InetSocketAddress(9001));
            // 连接服务端
            socket.connect(new InetSocketAddress(9000));
            // 设置超时时间，超时之后不在进行连接
            socket.setSoTimeout(1000);
            // 写入
            socket.getOutputStream().write("hello".getBytes(StandardCharsets.UTF_8));

            socket.getOutputStream().write('A');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
