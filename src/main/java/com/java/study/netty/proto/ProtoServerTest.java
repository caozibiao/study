package com.java.study.netty.proto;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

/**
 * @Author zibiao cao
 * @date 2020/12/8 15:36
 * @Version 1.0
 */
public class ProtoServerTest {
    public static void main(String[] args) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap().group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("decoder",new ProtobufDecoder(MyDataInfo.MyMessage.getDefaultInstance())).addLast(new SimpleChannelInboundHandler<MyDataInfo.MyMessage>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyDataInfo.MyMessage myMessage) throws Exception {
                                    if(myMessage.getDataType() == MyDataInfo.MyMessage.DataType.StudentType) {
                                        System.out.println(myMessage.getStudent());
                                    }

                                    if(myMessage.getDataType() == MyDataInfo.MyMessage.DataType.TeacherType) {
                                        System.out.println(myMessage.getTeacher());
                                    }
                                }
                            });
                        }
                    });
            ChannelFuture future = serverBootstrap.bind(7001).sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
