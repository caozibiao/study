package com.java.study.netty;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @Author zibiao cao
 * @date 2020/12/7 15:19
 * @Version 1.0
 */
public class NioClientTest2 {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.socket().connect(new InetSocketAddress(6666));
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();

        socketChannel.register(selector, SelectionKey.OP_READ| SelectionKey.OP_WRITE);

        ByteBuffer byteBuffer = ByteBuffer.wrap("hello".getBytes());

        socketChannel.write(byteBuffer);
    }
}
