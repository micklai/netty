package com.mk.daoheng.netty.byteBuff;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

public class Test1 {

    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("长helle world", Charset.forName("utf-8"));  //创建堆内存
        Unpooled.directBuffer(10); //创建直接内存
        if (byteBuf.hasArray()) { //是否是堆商缓冲
            byte[] array = byteBuf.array();
            System.out.println(new java.lang.String(array, Charset.forName("utf-8")));
            System.out.println(byteBuf);
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.capacity());

            int length = byteBuf.readableBytes();  // writeIndex - readIndex

            byteBuf.readBytes(3);
            for (int i = 0; i < length; i++) {
                System.out.println(byteBuf.getByte(i));
            }

            System.out.println(byteBuf.getCharSequence(2, 4, Charset.forName("utf-8")));//从第2个开始打印4个字节，可能会出现乱码

            // isReadable 是否可继续读  writeIndex > readIndex

            // isWritable 是否可继续写 writeIndex < capacity

            // claer  writeIndex = 0 and readIndex = 0

            // discardReadBytes  //读过的废弃， readIndex = 0 ,writeIndex 为刚刚的readIndex的值

            // netty butebuff提供三种缓冲区类型
                    // heap buffer 堆
                    //direct buffer 直接
                    //composite buffer 复合缓冲区，合又堆，直接缓冲区组成

            // maxCapacity buff的最大值，就是Integer.MAX_VALUE
        }
    }
}
