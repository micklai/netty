package com.mk.daoheng.netty.byteBuff;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Iterator;

public class Test2 {

    public static void main(String[] args) {
            // netty butebuff提供三种缓冲区类型
                    // heap buffer 堆 实际缓存 byte array 由于数据存放在堆中，可以快速创建，快速释放，提供了直接访问内部数组的方法
                                        //缺点：读写是需要放到直接缓冲区中进行网络传输

                    //direct buffer 直接  在堆之外直接分配空间，不会占用堆得空间，是由操作系统在本地进行的数据分配 ， 优点：在使用数据传递时，性能非常好
                                    //因为数据是直接位于操作系统内存中，所以不需要从JVM讲数据复制到直接缓冲区中
                                    //缺点，因为direct buffer 是直接在操作系统的内存中的，所以空间的分配想多复杂，且释放空间比较慢
                                    //netty 通过提供内存池解决这个问题。Direct缓冲区并不支持
                                    //对于后端的业务消息的编解码来说，推荐使用HeapBuf ，对于I/O通信进行读写缓冲区，推荐使用DirectByteBuff

                    //composite buffer 复合缓冲区，合又堆，直接缓冲区支持听过字节数组的方式来访问数据

            // netty 的buff使用读写分离，而jdk则不是
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();

        ByteBuf heapBuffer = Unpooled.buffer(10);
        ByteBuf directBuffer = Unpooled.directBuffer(10);

        compositeByteBuf.addComponents(heapBuffer,directBuffer);
//        compositeByteBuf.removeComponent(0);  // 相当于删除heapBuffer

        Iterator<ByteBuf> iterator = compositeByteBuf.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        compositeByteBuf.forEach(System.out::println);

        compositeByteBuf.forEach(byteBuf -> {
            System.out.println(byteBuf);
        });

    }
}
