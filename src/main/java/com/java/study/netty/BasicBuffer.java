package com.java.study.netty;

import java.nio.LongBuffer;

public class BasicBuffer {
    public static void main(String[] args) {
        LongBuffer longBuffer = LongBuffer.allocate(4);
        System.out.println(longBuffer.mark() + "," + longBuffer.position()
        + "," + longBuffer.limit() + "," + longBuffer.capacity());
        longBuffer.put(1234);
        longBuffer.put(12345);
        longBuffer.put(12346);
        longBuffer.put(12347);

        System.out.println("before flip: " + longBuffer.mark() + "," + longBuffer.position()
                + "," + longBuffer.limit() + "," + longBuffer.capacity());

        longBuffer.flip();

        System.out.println("after flip: " + longBuffer.mark() + "," + longBuffer.position()
                + "," + longBuffer.limit() + "," + longBuffer.capacity());

        while(longBuffer.hasRemaining()) {
            System.out.println(longBuffer.get());
            System.out.println("after get: " + longBuffer.mark() + "," + longBuffer.position()
                    + "," + longBuffer.limit() + "," + longBuffer.capacity());
        }
    }
}
