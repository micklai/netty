package com.mk.daoheng.netty.NioTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8899));

        while (true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for(SelectionKey selectionKey: selectionKeys){
                if(selectionKey.isConnectable()){//已经完成第一次握手
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    if(channel.isConnectionPending()){
                        channel.finishConnect(); // 连接真正建立好

                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        byteBuffer.put((LocalDateTime.now() + "连接成功").getBytes() );
                        channel.write(byteBuffer);

                        ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                        executorService.submit(() -> {
                            while(true){
                                byteBuffer.clear();
                                InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                                String msg = bufferedReader.readLine();

                                byteBuffer.put(msg.getBytes());
                                channel.write(byteBuffer);
                            }
                        });
                    }
                    channel.register(selector,SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
                    SocketChannel client = (SocketChannel)selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    Integer length = null;
                    while (true){
                        length = client.read(byteBuffer);
                        if(length <= 0){
                            break;
                        }
                        byteBuffer.flip();
                        String msg = "你好";
                        byteBuffer.put(msg.getBytes());
                        client.write(byteBuffer);
                    }
                }
            }
            selectionKeys.clear();
        }
    }
}
