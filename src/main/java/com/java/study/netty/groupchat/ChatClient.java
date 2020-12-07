package com.java.study.netty.groupchat;

import org.apache.ibatis.annotations.Select;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @Author zibiao cao
 * @date 2020/12/7 16:45
 * @Version 1.0
 */
public class ChatClient {
    private SocketChannel clientChannel;
    private Selector selector;
    private final String address = "127.0.0.1";
    private final int port = 8010;
    private String username;
    public ChatClient() {
        try{
            clientChannel = SocketChannel.open();
            clientChannel.connect(new InetSocketAddress(address, port));
            clientChannel.configureBlocking(false);
            selector = Selector.open();
            clientChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            username = clientChannel.getLocalAddress().toString();
            System.out.println(username + " is ok ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendInfo(String info) {
        info = username + ": " + info;
        try{
            clientChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readInfo(){
        try {
            int select = selector.select();
            if(select > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while(iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if(selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel)selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        channel.read(byteBuffer);
                        System.out.println(byteBuffer.array());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient();

        new Thread() {
          @Override
          public void run() {
              while(true) {
                  chatClient.readInfo();
                  try{
                      Thread.currentThread().sleep(3000);
                  } catch (Exception e) {
                      e.printStackTrace();
                  }

              }
          }
        }.start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            chatClient.sendInfo(scanner.nextLine());
        }
    }
}
