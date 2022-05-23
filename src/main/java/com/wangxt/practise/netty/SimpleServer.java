package com.wangxt.practise.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleServer {


    public static void main(String[] args) {
        // 创建 eventGroup，负责处理连接事件
        EventLoopGroup connectGroup = new NioEventLoopGroup(1);
        // 创建 eventGroup，负责处理读写事件
        EventLoopGroup workerGroup = new NioEventLoopGroup(1);

        try{
            // 创建 Server启动器
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 注册eventGroup
            serverBootstrap.group(connectGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 10)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                            // 将自定义的处理器加入管道
                            nioSocketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            // 绑定端口
            ChannelFuture future = serverBootstrap.bind(9001).sync();
            Map<Integer,Integer> map = new HashMap<>();
            String str = "";
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connectGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }


}

class NettyServerHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.printf("%s注册了", ctx.channel().remoteAddress());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.printf("%s连接了", ctx.channel().remoteAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.printf("[%s] 说：%s", ctx.channel().remoteAddress(), ((ByteBuf)msg).toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("收到，over".getBytes(StandardCharsets.UTF_8)));
    }
}
