package com.wangxt.practise.socket.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.util.*;

public class NioOfSelector {

    public static void main(String[] args) throws Exception{
        // 打开一个管道
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        // 建立socket,绑定端口
        socketChannel.socket().bind(new InetSocketAddress(9000));
        // 设置socketChannel为非阻塞
        socketChannel.configureBlocking(false);
        // 设置selector
        Selector selector = Selector.open();
        // 将channel注册到selector,监听连接事件,返回该事件key,注册到selector的channel必须是非阻塞的，否则会抛出异常
        SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(true){
            // 阻塞，直到有监听的事件发生，为什么是阻塞的
            int select = selector.select();

            // 监听到有事件发生，遍历处理事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while(iterator.hasNext()){
                SelectionKey sk = iterator.next();
                if(sk.isAcceptable()){
                    // 如果是监听的连接事件,再给channel注册读事件,由于监听连接事件是由ServerSocketChannel注册的，所以获取道德channel是ServerSocketChannel
                    ServerSocketChannel channel = (ServerSocketChannel) sk.channel();
                    // 上边设置了非阻塞
                    SocketChannel accept = channel.accept();
                    // 设置读非阻塞
                    accept.configureBlocking(false);
                    // 设置该channel监听读事件
                    SelectionKey register = accept.register(selector, SelectionKey.OP_READ);
                    // 删除连接监听
                    iterator.remove();
                }else if(sk.isReadable()){
                    // 如果是读事件就读数据
                    SocketChannel sc = (SocketChannel) sk.channel();
                    read(sc);
                }
            }
        }
    }

    private static void read(SocketChannel accept) throws Exception{

        ByteBuffer byteBuffer = MappedByteBuffer.allocate(1024);
        int read = accept.read(byteBuffer);
        if(read > 0){
            System.out.println("读取客户端数据");
            System.out.println("客户端【" + accept.socket().getPort() + "】说："+ new String(byteBuffer.array()));
        }
    }
}
