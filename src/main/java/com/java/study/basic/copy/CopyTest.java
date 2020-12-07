package com.java.study.basic.copy;

public class CopyTest implements Cloneable{
    public static void main(String[] args) {
        Object obj = new Object();

    }

    @Override
    public Object clone(){
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
