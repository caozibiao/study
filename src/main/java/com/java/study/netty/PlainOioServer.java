package com.java.study.netty;

import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PlainOioServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8090);

            while(true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("client socket " + clientSocket);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OutputStream out;
                        try {
                            out = clientSocket.getOutputStream();
                            out.write("hello\r\n".getBytes(CharsetUtil.UTF_8));
                            out.flush();
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
