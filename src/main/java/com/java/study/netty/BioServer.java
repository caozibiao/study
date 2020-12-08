package com.java.study.netty;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();

        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            System.out.println("server start!");
            while(true) {
                System.out.println("wait client connect, currentThread is " + Thread.currentThread());
                Socket client = serverSocket.accept();
                System.out.println("client connected, client is " + client);
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(Thread.currentThread() + " start");
                            byte[] bytes = new byte[1024];
                            InputStream inputStream = client.getInputStream();
                            while(true) {
                                System.out.println("wait input");
                                int read = inputStream.read(bytes);
                                if(read != -1) {
                                    System.out.println(Thread.currentThread() + ":" + new String(bytes, 0, read));
                                } else {
                                    break;
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
