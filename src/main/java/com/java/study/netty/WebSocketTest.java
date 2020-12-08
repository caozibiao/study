package com.java.study.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Author zibiao cao
 * @date 2020/12/8 13:58
 * @Version 1.0
 */
public class WebSocketTest {
    public static void main(String[] args) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap().group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new HttpServerCodec())
                                    .addLast(new ChunkedWriteHandler())
                                    .addLast(new HttpObjectAggregator(8192))
                                    .addLast(new WebSocketServerProtocolHandler("/hello"))
                                    .addLast(new SimpleChannelInboundHandler<TextWebSocketFrame>() {
                                        @Override
                                        protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
                                            System.out.println("server receive msg: " + textWebSocketFrame.text());
                                            channelHandlerContext.writeAndFlush(new TextWebSocketFrame("server response: " + textWebSocketFrame.text()));
                                        }
                                    });
                        }

                        @Override
                        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                            System.out.println("handlerAdded 被调用" + ctx.channel().id().asLongText());
                            System.out.println(ctx.channel().id().asShortText());
                        }

                        @Override
                        public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
                            System.out.println("handlerRemoved 被调用" + ctx.channel().id().asLongText());
                        }

                        @Override
                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                            cause.printStackTrace();
                            ctx.close();
                        }
                    });
            ChannelFuture future = serverBootstrap.bind(7000).sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
