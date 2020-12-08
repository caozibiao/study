package com.java.study.netty.code;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @Author zibiao cao
 * @date 2020/12/8 17:05
 * @Version 1.0
 */
public class NettyCodeClient {
    public static void main(String[] args) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap().group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new MessageToMessageDecoder<ByteBuf>() {
                                @Override
                                protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
                                    if(in.readableBytes() >= 8) {
                                        list.add(in.readLong());
                                    }
                                }
                            }).addLast(new MessageToByteEncoder<Long>() {
                                @Override
                                protected void encode(ChannelHandlerContext channelHandlerContext, Long aLong, ByteBuf byteBuf) throws Exception {
                                    System.out.println("---client encode---");
                                    byteBuf.writeLong(aLong);
                                }
                            }).addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    System.out.println("-----client active----");
                                    ctx.writeAndFlush(12345l);
                                }

                                @Override
                                public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
                                    System.out.println("----client read------:"  + msg);
                                }
                            });
                        }
                    });
            ChannelFuture future = bootstrap.connect("127.0.0.1", 7002).sync();
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
