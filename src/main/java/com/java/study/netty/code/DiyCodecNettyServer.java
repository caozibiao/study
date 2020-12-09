package com.java.study.netty.code;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;
import java.util.UUID;

public class DiyCodecNettyServer {
    public static void main(String[] args) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap().group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                            .addLast(new ReplayingDecoder<Void>() {
                                @Override
                                protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
                                    int length = in.readInt();
                                    byte [] content = new byte[length];
                                    in.readBytes(content);
                                    MyMessage myMessage = new MyMessage();
                                    myMessage.setContent(content);
                                    myMessage.setLength(length);
                                    out.add(myMessage);
                                }
                            })
                            .addLast(new MessageToByteEncoder<MyMessage>() {
                                @Override
                                protected void encode(ChannelHandlerContext ctx, MyMessage msg, ByteBuf out) throws Exception {
                                    out.writeInt(msg.getLength());
                                    out.writeBytes(msg.getContent());
                                }
                            })
                            .addLast(new SimpleChannelInboundHandler<MyMessage>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, MyMessage msg) throws Exception {
                                    System.out.println("----server read----");
                                    System.out.println("msg length: " + msg.getLength() + ", msg content: " + new String(msg.getContent()));
                                    ctx.writeAndFlush(new MyMessage(UUID.randomUUID() + ": " + new String(msg.getContent())));
                                }
                            });
                        }
                    });
            ChannelFuture future = serverBootstrap.bind(7009).sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
