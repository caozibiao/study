package com.java.study.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class ByteBufTest {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("test", CharsetUtil.UTF_8);
        System.out.println("readerIndex: " + byteBuf.readerIndex());
        System.out.println("writerIndex: " + byteBuf.writerIndex());
        System.out.println(byteBuf.duplicate());
    }
}
