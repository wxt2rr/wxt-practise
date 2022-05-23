package com.wangxt.practise.socket.bio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/**
 * 实现一个非阻塞的bio
 * 主要修改两点，accept 和 read 方法不在阻塞
 */
public class BioOfNoBlock {

    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket();
        // 绑定并监听
        serverSocket.bind(new InetSocketAddress(9001));
        // 设置accept超时时间为1s
        serverSocket.setSoTimeout(1000);
        // 获取客户端连接
        Socket socket = null;
        while (true){
            try{
                socket = serverSocket.accept();
                System.out.println("客户端连接");
            }catch (Exception e){
                // 等待accept超时，跳过处理
                System.out.println("没有客户端连接");
                continue;
            }

            if(Objects.nonNull(socket)){
                // 这里也设置 read 的超时时间
                socket.setSoTimeout(1000);

                byte[] arr = new byte[1024];
                int read = -1;
                while (true){
                    try{
                        read = socket.getInputStream().read(arr);
                        System.out.println("读取客户端数据");
                        break;
                    }catch (Exception e){
                        // 等待读超时
                        System.out.println("等待客户端写数据");
                    }
                }

                if(read > 0){
                    System.out.println("客户端说：" + new String(arr));
                }
            }
        }
    }
}
