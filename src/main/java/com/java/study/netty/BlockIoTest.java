package com.java.study.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockIoTest {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8077);
            Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String request, response;
            while((request = in.readLine()) != null) {
                if("Done".equals(request)) {
                    break;
                }
                response = "response";
                out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
