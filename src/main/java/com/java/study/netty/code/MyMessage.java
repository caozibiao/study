package com.java.study.netty.code;

public class MyMessage {
    private int length;
    private byte[] content;

    public MyMessage() {

    }

    public MyMessage(String content) {
        this.length = content.getBytes().length;
        this.content = content.getBytes();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
