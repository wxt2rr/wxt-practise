package com.wangxt.practise.socket.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class NioOfPoll {
    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9001));

        serverSocketChannel.configureBlocking(false);

        List<SocketChannel> list = new ArrayList<>();
        while(true){
            SocketChannel accept = serverSocketChannel.accept();
            // 有客户端连接,有可能连接了但是没有进行数据io，所以需要将socket连接存起来，直到有数据io
            if(accept != null){
                list.add(accept);
            }

            // 遍历所有连接成功的客户端是否有数据io
            for(SocketChannel channel : list){
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int read = channel.read(byteBuffer);
                if(read > 0){
                    System.out.println(new String(byteBuffer.array()));
                }
            }
        }
    }
}
