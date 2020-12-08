package com.java.study.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Author zibiao cao
 * @date 2020/12/8 13:29
 * @Version 1.0
 */
public class IdleHandlerTest {
    public static void main(String[] args) throws Exception{
        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap().group(boosGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new IdleStateHandler(3, 5, 6, TimeUnit.SECONDS))
                                    .addLast(new ChannelInboundHandlerAdapter(){
                                        @Override
                                        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                            if(evt instanceof IdleStateEvent) {
                                                IdleStateEvent idleStateEvent = (IdleStateEvent)evt;
                                                switch (idleStateEvent.state()){
                                                    case ALL_IDLE:
                                                        System.out.println("------all idle--------");
                                                        break;
                                                    case READER_IDLE:
                                                        System.out.println("------reader idle--------");
                                                        break;
                                                    case WRITER_IDLE:
                                                        System.out.println("------writer idle--------");
                                                        break;
                                                }
                                            }
                                        }
                                    });
                        }
                    });
            ChannelFuture future = serverBootstrap.bind(7000).sync();
            future.channel().closeFuture().sync();

        } finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
