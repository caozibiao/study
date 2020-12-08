package com.java.study.netty.proto;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

import java.util.Random;

/**
 * @Author zibiao cao
 * @date 2020/12/8 15:47
 * @Version 1.0
 */
public class ProtoClientTest {
    public static void main(String[] args) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap().group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("encoder", new ProtobufEncoder())
                                    .addLast(new ChannelInboundHandlerAdapter(){
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    int random = new Random().nextInt(3);
                                    MyDataInfo.MyMessage myMessage = null;
                                    if(random == 0) {
                                        myMessage = MyDataInfo.MyMessage.newBuilder()
                                                .setDataType(MyDataInfo.MyMessage.DataType.StudentType)
                                                .setStudent(MyDataInfo.Student.newBuilder().setId(1).setName("zhangsan")).build();
                                    } else{
                                        myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DataType.TeacherType)
                                                .setTeacher(MyDataInfo.Teacher.newBuilder().setName("lisi").setAge(30)).build();
                                    }
                                    ctx.writeAndFlush(myMessage);
                                }
                            });
                        }
                    });
            ChannelFuture future = bootstrap.connect("127.0.0.1", 7001).sync();
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
