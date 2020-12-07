package com.java.study.netty;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


public class FileChannelReadTest {
    public static void main(String[] args) {
        try{
            File file = new File("./file.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
            FileChannel channel = fileInputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());

            int read = channel.read(byteBuffer);

            System.out.println(new String(byteBuffer.array()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
