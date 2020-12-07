package com.java.study.netty.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @Author zibiao cao
 * @date 2020/12/7 15:59
 * @Version 1.0
 */
public class ChatServer {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    private final static  int port = 8010;

    public ChatServer() {
        try{
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        while(true) {
            try{
                int select = selector.select();

                if(select <= 0) {
                    System.out.println("------继续监听-----");
                    continue;
                }

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if(selectionKey.isAcceptable()) {
                        SocketChannel client = serverSocketChannel.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        System.out.println(client.getRemoteAddress() + "上线");
                    }

                    if(selectionKey.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                        socketChannel.configureBlocking(false);
                        readData(socketChannel);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readData(SocketChannel socketChannel) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try{
            int read = socketChannel.read(byteBuffer);
            if(read > 0) {
                byte[] msg = byteBuffer.array();
                System.out.println(socketChannel.getRemoteAddress() + ": " + new String(msg));
                sendMsgToOtherClients(msg, socketChannel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMsgToOtherClients(byte[] msg, SocketChannel selfChannel) {
        for(SelectionKey key: selector.keys()) {
            SelectableChannel channel = key.channel();
            if(channel instanceof SocketChannel && channel != selfChannel) {
                SocketChannel otherChannel = (SocketChannel)channel;
                try{
                    otherChannel.write(ByteBuffer.wrap(msg));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new ChatServer().listen();
    }
}
