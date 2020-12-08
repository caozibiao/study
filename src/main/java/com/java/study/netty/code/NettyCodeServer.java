package com.java.study.netty.code;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @Author zibiao cao
 * @date 2020/12/8 16:56
 * @Version 1.0
 */
public class NettyCodeServer {
    public static void main(String[] args) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap().group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new MessageToMessageDecoder<ByteBuf>() {
                                @Override
                                protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
                                    System.out.println("---server decode----");
                                    if(in.readableBytes() >= 8) {
                                        list.add(in.readLong());
                                    }
                                }
                            }).addLast(new MessageToByteEncoder<Long>() {
                                @Override
                                protected void encode(ChannelHandlerContext channelHandlerContext, Long aLong, ByteBuf byteBuf) throws Exception {
                                    byteBuf.writeLong(aLong);
                                }
                            }).addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelRead(ChannelHandlerContext channelHandlerContext, Object aLong) throws Exception {
                                    System.out.println("----server response------" + "long: " + aLong);
                                    channelHandlerContext.writeAndFlush(1234567L );
                                }
                            });
                        }
                    });
            ChannelFuture future = serverBootstrap.bind(7002).sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
