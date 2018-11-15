package com.mk.daoheng.netty.NioTest;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ZeroCopy {

    public static void main(String[] args) throws  Exception{
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8899));

        while (true){
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            DataInputStream bufferedInputStream = new DataInputStream(inputStream);

            while (true){
                byte[] bytes = new byte[1024];
                int length = bufferedInputStream.read(bytes);
                if(length < 0){
                    break;
                }
            }
        }
    }
}
