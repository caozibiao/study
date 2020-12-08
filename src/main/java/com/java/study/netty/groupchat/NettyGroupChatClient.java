package com.java.study.netty.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @Author zibiao cao
 * @date 2020/12/8 10:50
 * @Version 1.0
 */
public class NettyGroupChatClient {
    private final String ip;
    private final int port;

    public NettyGroupChatClient(String ip, int port) {
        this.port = port;
        this.ip = ip;
    }

    public void run() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap().group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new StringDecoder())
                                    .addLast(new StringEncoder())
                                    .addLast(new SimpleChannelInboundHandler<String>() {
                                        @Override
                                        public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                            System.out.println(msg.trim());
                                        }
                                    });
                        }
                    });
            ChannelFuture future = bootstrap.connect(ip, port).sync();
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNext()) {
                String msg = scanner.nextLine();
                future.channel().writeAndFlush(msg + "\r\n");
            }
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws Exception{
        new NettyGroupChatClient("127.0.0.1", 7000).run();
    }
}
