package com.java.study.netty;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelCopyTest {
    public static void main(String[] args) throws Exception{
        File file = new File("./file.txt");
        FileInputStream inputStream = new FileInputStream(file);

        FileOutputStream fileOutputStream = new FileOutputStream(new File("./file3.txt"));
        FileChannel writeChannel = fileOutputStream.getChannel();

        FileChannel readChannel = inputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while(true) {
            byteBuffer.clear();
            int read = readChannel.read(byteBuffer);

            if(read == -1) {
                break;
            }
            byteBuffer.flip();
            writeChannel.write(byteBuffer);
        }
    }
}
