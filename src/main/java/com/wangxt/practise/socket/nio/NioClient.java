package com.wangxt.practise.socket.nio;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;

public class NioClient {

    public static void main(String[] args) {
        try (SocketChannel channel = SocketChannel.open()) {

            channel.bind(new InetSocketAddress(9000));
            channel.connect(new InetSocketAddress(9001));

            channel.configureBlocking(false);


            ByteBuffer allocate = ByteBuffer.allocate(1024);
            allocate.put("我是客户端".getBytes(StandardCharsets.UTF_8));

            Charset utf8 = StandardCharsets.UTF_8;
            CharsetDecoder decoder = utf8.newDecoder();

            CharBuffer decode = decoder.decode(allocate);
            channel.write(allocate);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
