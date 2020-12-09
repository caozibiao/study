package com.java.study.netty.code;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;


public class DiyCodecNettyClient {
    public static void main(String[] args) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap().group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new MessageToByteEncoder<MyMessage>() {
                                @Override
                                protected void encode(ChannelHandlerContext ctx, MyMessage msg, ByteBuf out) throws Exception {
                                    System.out.println("----client encode---");
                                    out.writeInt(msg.getLength());
                                    out.writeBytes(msg.getContent());
                                }
                            })
                            .addLast(new ReplayingDecoder<Void>() {
                                @Override
                                protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
                                    int length = in.readInt();
                                    byte [] content = new byte[length];
                                    in.readBytes(content);
                                    MyMessage myMessage = new MyMessage();
                                    myMessage.setLength(length);
                                    myMessage.setContent(content);
                                    out.add(myMessage);
                                }
                            })
                            .addLast(new SimpleChannelInboundHandler<MyMessage>() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    System.out.println("---client active----");
                                    for(int i = 0; i < 5; i++) {
                                        ctx.writeAndFlush(new MyMessage("test diy codec" + i));
                                    }
                                }

                                @Override
                                protected void channelRead0(ChannelHandlerContext ctx, MyMessage msg) throws Exception {
                                    System.out.println("---client read--");
                                    System.out.println(new String(msg.getContent()));
                                }
                            });
                        }
                    });
            ChannelFuture future = bootstrap.connect("127.0.0.1", 7009).sync();
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
