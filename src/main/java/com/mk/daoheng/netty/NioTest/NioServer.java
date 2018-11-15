package com.mk.daoheng.netty.NioTest;

import com.google.common.collect.Maps;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class NioServer {

    private static Map<String,SocketChannel> clientMap = Maps.newHashMap();

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            try {
                final int select = selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach((SelectionKey selectionKey) -> {
                    try {
                        if(selectionKey.isAcceptable()){
                            ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel)selectionKey.channel();//这里先获取ServerSocketChannel
                            SocketChannel socketChannel = serverSocketChannel1.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector,SelectionKey.OP_READ);
                            selectionKeys.remove(selectionKey);
                            String key = "["+ UUID.randomUUID().toString()+"]";
                            clientMap.put(key,socketChannel);

                            System.out.println(socketChannel.getRemoteAddress() + "链接");

                        }else if(selectionKey.isReadable()){
                            SocketChannel channel = (SocketChannel)selectionKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
                            String msg = "";
                            while (true) {
                                byteBuffer.clear();
                                int read = channel.read(byteBuffer);
                                if(read <= 0){
                                    break;
                                }
                                Charset charset = Charset.forName("utf-8");
                                String message = String.valueOf(charset.decode(byteBuffer).array());
                                msg += message;
                                byteBuffer.flip();
                                channel.write(byteBuffer);
                            }
//                            clientMap.forEach((String key, SocketChannel value) -> {
//                                ByteBuffer buff = ByteBuffer.allocate(msg.getBytes().length);
//                                value.write(ByteBuffer.msg.getBytes());
//                            });

                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
