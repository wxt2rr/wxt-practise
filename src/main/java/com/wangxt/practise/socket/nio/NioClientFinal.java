package com.wangxt.practise.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public class NioClientFinal {

    public static void main(String[] args) throws IOException {
        SocketChannel open = SocketChannel.open();
        open.configureBlocking(false);

        open.bind(new InetSocketAddress(9000));
        open.connect(new InetSocketAddress(9001));


        Socket socket = open.socket();
    }
}
