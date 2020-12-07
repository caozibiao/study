package com.java.study.netty;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class TransferToTest {
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream(new File("./file.txt"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("./file4.txt"));

        FileChannel sourceChannel = fileInputStream.getChannel();
        FileChannel destinationChannel = fileOutputStream.getChannel();

        sourceChannel.transferTo(0 , sourceChannel.size(), destinationChannel);

        sourceChannel.close();
        destinationChannel.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
