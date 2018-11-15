package com.mk.daoheng.netty.NioTest;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorTest {

    public static void main(String[] args) throws  Exception {
        /*ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8899));
        Socket accept = serverSocket.accept();
        accept.getChannel();*/

        int[] posts = new int[5];
        posts[0] = 5000;
        posts[1] = 5001;
        posts[2] = 5002;
        posts[3] = 5003;
        posts[4] = 5004;

        Selector selector = Selector.open();

        for(int i=0 ;i<posts.length;i++){
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);

            ServerSocket serverSocket = serverSocketChannel.socket();
            serverSocket.bind(new InetSocketAddress(posts[i]));

            serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        }
        while (true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (true){
                if(iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    if(selectionKey.isAcceptable()){
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);

                        System.out.println(socketChannel.getLocalAddress() + "已連接:");
                        System.out.println(socketChannel.getRemoteAddress() + "已連接:");

                        socketChannel.register(selector,SelectionKey.OP_READ);

                        iterator.remove();
                    }else if(selectionKey.isReadable()){
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int read = 0;
                        while (true){
                            byteBuffer.clear();
                            read = socketChannel.read(byteBuffer);
                            if(read <= 0){
                                break;
                            }
                            byteBuffer.flip();
                            socketChannel.write(byteBuffer);
                        }
                        iterator.remove();
                    }
                }
                break;
            }

        }
    }
}
