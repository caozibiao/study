package com.java.study.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class NettyNioServer {
    public static void main(String[] args) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        final ByteBuf byteBuf = Unpooled.copiedBuffer("hello\r\n", CharsetUtil.UTF_8);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try{
            serverBootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(8093))
                    .childHandler(new ChannelInitializer<SocketChannel>(){
                       @Override
                       protected void initChannel(SocketChannel socketChannel) {
                           socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                               @Override
                               public void channelActive(ChannelHandlerContext ctx){
                                   ctx.writeAndFlush(byteBuf.duplicate()).addListener(ChannelFutureListener.CLOSE);
                               }
                           });
                       }
                    });
            ChannelFuture future = serverBootstrap.bind().sync();
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
