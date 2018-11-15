package com.mk.daoheng.netty.NioTest;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NioZeroCopyClient {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(true);
        socketChannel.connect(new InetSocketAddress("localhost",8899));

        FileChannel channel = new FileInputStream("123.text").getChannel();

        //0拷贝发送
        channel.transferTo(0,channel.size(),socketChannel);

        channel.close();


    }
}
