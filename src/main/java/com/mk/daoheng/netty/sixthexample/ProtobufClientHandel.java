package com.mk.daoheng.netty.sixthexample;

import com.mk.daoheng.protobuf.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtobufClientHandel extends SimpleChannelInboundHandler<DataInfo.Mymessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Mymessage msg) throws Exception {
        System.out.println(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {



//        DataInfo.Mymessage student = DataInfo.Mymessage.newBuilder().setName("张娜").setAge(20).setAddress("上海").build();
//        ctx.channel().writeAndFlush(student);
    }
}
