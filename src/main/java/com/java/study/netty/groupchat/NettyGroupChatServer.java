package com.java.study.netty.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Author zibiao cao
 * @date 2020/12/8 10:29
 * @Version 1.0
 */
public class NettyGroupChatServer {
    private final int port;
    private ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public NettyGroupChatServer(int port){
        this.port = port;
    }

    public void run() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new StringDecoder())
                                    .addLast(new StringEncoder())
                                    .addLast(new SimpleChannelInboundHandler<String>() {

                                        @Override
                                        public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                                            channelGroup.add(ctx.channel());
                                            channelGroup.writeAndFlush("client " + ctx.channel().remoteAddress() + " 进入群聊\n");
                                            System.out.println("group size: " + channelGroup.size());
                                        }

                                        @Override
                                        public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
                                            channelGroup.writeAndFlush("client " + ctx.channel().remoteAddress() + " 退出群聊\n");
                                            System.out.println("group size: " + channelGroup.size());
                                        }

                                        @Override
                                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                            System.out.println("client " + ctx.channel().remoteAddress() + " 上线了\n");
                                        }

                                        @Override
                                        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                            System.out.println("client " + ctx.channel().remoteAddress() + " 下线了\n");
                                        }

                                        @Override
                                        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                            channelGroup.forEach(channel -> {
                                                if(channel != ctx.channel()) {
                                                    channel.writeAndFlush("cliend send msg: " + msg + "\n");
                                                }
                                            });
                                        }
                                    });
                        }
                    });
            ChannelFuture future = serverBootstrap.bind(port).sync();
            System.out.println("server start!");
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception{
        new NettyGroupChatServer(7000).run();
    }
}
