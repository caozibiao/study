package com.java.study.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.springframework.http.HttpStatus;

import java.net.InetSocketAddress;
import java.net.URI;

public class HttpNettyServer {
    public static void main(String[] args) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try{
            serverBootstrap.group(bossGroup, workGroup)
                    .localAddress(new InetSocketAddress(8050))
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new HttpServerCodec()).addLast(
                                    new SimpleChannelInboundHandler<HttpObject>() {
                                        @Override
                                        protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
                                            System.out.println("current handler: " + ctx.handler());

                                            if(msg instanceof HttpRequest) {
                                                HttpRequest httpRequest = (HttpRequest) msg;
                                                //获取uri, 过滤指定的资源
                                                URI uri = new URI(httpRequest.uri());
                                                if ("/favicon.ico".equals(uri.getPath())) {
                                                    System.out.println("请求了 favicon.ico, 不做响应");
                                                    return;
                                                }
                                                System.out.println("remote address: " + ctx.channel().remoteAddress());
                                                ByteBuf byteBuf = Unpooled.copiedBuffer("hello client", CharsetUtil.UTF_8);

                                                FullHttpResponse response = new DefaultFullHttpResponse(
                                                        HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
                                                response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json");
                                                response.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
                                                ctx.writeAndFlush(response);

                                            }
                                        }
                                    });
                        }
                    });
            ChannelFuture future = serverBootstrap.bind().sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
