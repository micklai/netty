package com.mk.daoheng.netty.byteBuff;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class test0 {

    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.buffer(10);

        for(int i=0;i<byteBuf.capacity();i++){
            byteBuf.writeByte(i);
        }

        byteBuf.getByte(1);//readIndex不会加一
        byteBuf.readByte();    //readIndex 会加一


        System.out.println( Unpooled.directBuffer(10));
    }
}
