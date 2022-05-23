package com.wangxt.practise.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NioServerFinal {

    public static void main(String[] args) {
        try {
            ServerSocketChannel open = ServerSocketChannel.open();
            // bind and listen
            open.bind(new InetSocketAddress(9001));
            // set no-block
            open.configureBlocking(false);
            // open a selector fd, epoll_create
            Selector selector = Selector.open();
            // register channel to selector,add channel to pollWapper , return the sk,sk link channel and selector
            SelectionKey register = open.register(selector, SelectionKey.OP_ACCEPT);

            ByteBuffer readBuffer = ByteBuffer.allocate(10);

            while (true){
                // block method
                int select = selector.select();
                // get sk
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    if(selectionKey.isAcceptable()){
                        // get a connect socket
                        ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel accept = channel.accept();
                        accept.configureBlocking(false);
                        // listen read
                        accept.register(selector, SelectionKey.OP_READ);
                        System.out.println("客户端链接");
                    }else if(selectionKey.isReadable()){
                        SocketChannel channel = (SocketChannel) selectionKey.channel();

                        readBuffer.clear();

                        int read;
                        StringBuilder stringBuilder = new StringBuilder();
                        // now , the read buffer is write
                        while((read = channel.read(readBuffer)) > 0){
                            // flip read
                            readBuffer.flip();
                            stringBuilder.append(new String(readBuffer.array()));
                            // clear
                            readBuffer.clear();
                        }

                        // 解码
                        Charset charset = StandardCharsets.UTF_8;
                        System.out.println("接受完客户端数据：" + stringBuilder.toString());

                        // flip write
                        //readBuffer.flip();
                        byte[] bytes = "收到".getBytes(StandardCharsets.UTF_8);
                        readBuffer.put(bytes);
                        channel.write(readBuffer);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
