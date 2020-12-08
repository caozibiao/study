package com.java.study.netty;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileChannelTest {
    public static void main(String[] args) {
        File file = new File("./file.txt");
        try{
            FileOutputStream out = new FileOutputStream(file);

            FileChannel channel = out.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put("hello".getBytes(Charset.forName("utf8")));

            // 注意
            byteBuffer.flip();

            channel.write(byteBuffer);

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
