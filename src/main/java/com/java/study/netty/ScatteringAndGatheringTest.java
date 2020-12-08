package com.java.study.netty;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class ScatteringAndGatheringTest {
    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(7000));

        ByteBuffer [] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(6);
        byteBuffers[1] = ByteBuffer.allocate(4);

        System.out.println("server started, wait client connect");

        SocketChannel client = serverSocketChannel.accept();

        while(true) {
            int byteRead = 0;
            long read = client.read(byteBuffers);
            byteRead++;
            System.out.println("byteRead:" + byteRead);

            Arrays.asList(byteBuffers).stream().map(byteBuffer -> "position:" + byteBuffer.position()
                    + "," + "limit:" + byteBuffer.limit()).forEach(System.out::println);

            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.flip());

            long write = client.write(byteBuffers);
            int byteWrite = 0;
            byteWrite++;
            System.out.println("byteWrite:" + byteWrite);

            Arrays.asList(byteBuffers).forEach(byteBuffer -> byteBuffer.clear());

            System.out.println(byteRead+ "," + byteWrite);
        }



    }
}
