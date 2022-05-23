package com.wangxt.practise.socket.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

/**
 * bio block io
 * 阻塞式io模型，哪里阻塞了：
 *  1.连接会阻塞，当accept方法监听没有客户端连接时会一直阻塞
 *  2.读取客户端消息会阻塞，即使客户端A连接了，accept方法不会阻塞，但是在客户端A真正发送消息之前，read方法又会一直阻塞
 *
 *  因为连接和读取数据都会阻塞，所以异步线程处理
 */
public class BioServer {

    public static void main(String[] args) throws Exception{
        // 创建服务端套接字
        ServerSocket socket = new ServerSocket();
        // 绑定端口，并且监听该端口是否有客户端连接
        socket.bind(new InetSocketAddress(9000));
        // 设置超时时间，超过时间accept不在进行阻塞等待
        socket.setSoTimeout(1000);

        int i =0;
        while (true){
            // 一直阻塞等待客户端连接,这里只能一个线程操作，多个线程也是阻塞，没有任何意义
            Socket accept = socket.accept();
            System.out.println(accept.getPort() + " 客户端连接了");

            // 这里可以搞个异步线程去处理，因为只有获取到客户端连接才会走到这里，搞个异步线程去读取该客户端的数据，主线程可以接着回去监听别的客户端的连接
            // 搞异步线程的话会有限制，一次来10w个请求根本处理不过来，并且还有线程的切换问题，性能开销
            /*new Thread(() -> {
                try {
                    read(accept);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            i++;
            System.out.println(i);*/
            /*list.add(accept);

            for(Socket socket1 : list){
                read(socket1);
            }*/

            read(accept);
        }
    }

    private static void read(Socket socket) throws IOException {
        byte[] bytes = new byte[1024];
        int read = socket.getInputStream().read(bytes);
        if(read > 0){
            System.out.println("客户端【" + socket.getPort()+ "】说："+ new String(bytes));
        }
    }
}
