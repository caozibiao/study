package com.java.study.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class TaskQueneTest {
    public static void main(String[] args) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>(){
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) {
                                    ctx.channel().eventLoop().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                Thread.sleep(10 * 1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            System.out.println("server thread: " + Thread.currentThread().getName() + ", channel: " + ctx.channel());
                                            ByteBuf byteBuf = (ByteBuf)msg;
                                            System.out.println("client address: " + ctx.channel().remoteAddress() +
                                                    "; client send msg: " + byteBuf.toString(CharsetUtil.UTF_8));
                                        }
                                    });

                                    ctx.channel().eventLoop().schedule(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                Thread.sleep(10 * 1000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            System.out.println("server thread: " + Thread.currentThread().getName() + ", channel: " + ctx.channel());
                                            ByteBuf byteBuf = (ByteBuf)msg;
                                            System.out.println("client address: " + ctx.channel().remoteAddress() +
                                                    "; client send msg: " + byteBuf.toString(CharsetUtil.UTF_8) + "1");
                                        }
                                    }, 5, TimeUnit.SECONDS);

                                }

                                @Override
                                public void channelReadComplete(ChannelHandlerContext ctx) {
                                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello, client", CharsetUtil.UTF_8));
                                }

                                @Override
                                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                    cause.printStackTrace();
                                    ctx.close();
                                }
                            });
                        }
                    });
            ChannelFuture future = serverBootstrap.bind(new InetSocketAddress(8900)).sync();
            future.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
