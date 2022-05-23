package com.wangxt.practise.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;

public class SimpleClient {
    public static void main(String[] args) {
        EventLoopGroup mainGroup = new NioEventLoopGroup(1);

        try{
            Bootstrap bootstrap = new Bootstrap().group(mainGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                    nioSocketChannel.pipeline().addLast(new NettyClientHandler());
                }
            });
            System.out.println("客户端启动成功");

            ChannelFuture future = bootstrap.connect("127.0.0.1", 9001).sync();
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mainGroup.shutdownGracefully();
        }
    }
}

class NettyClientHandler extends ChannelInboundHandlerAdapter {
    // 读逻辑
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(ctx.channel().remoteAddress() + "说：" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    // 读逻辑完成之后的逻辑
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("读完了");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("成功连接上服务端：" + ctx.channel().remoteAddress());
        ByteBuf byteBuf = Unpooled.copiedBuffer((ctx.channel().remoteAddress() + " 你好").getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端:"+ ctx.channel().remoteAddress() +"下线了");
    }
}
