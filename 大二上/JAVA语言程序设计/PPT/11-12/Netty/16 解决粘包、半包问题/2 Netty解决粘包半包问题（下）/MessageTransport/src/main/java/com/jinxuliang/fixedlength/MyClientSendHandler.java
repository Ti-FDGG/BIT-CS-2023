package com.jinxuliang.fixedlength;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

public class MyClientSendHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //这个ByteBuf在多次发送数据时，被重用
        ByteBuf buf = ctx.alloc().buffer();
        //分100次发送一个整数到服务端
        for (int i = 0; i < 100; i++) {
            //总是从第一个字节开始写
            buf.clear();
            buf.writeInt(i);
            //通知Netty，这个ByteBuf在数据发送期间需要保留
            buf.retain();
            System.out.println("发送：" + i);
            ctx.writeAndFlush(buf);
        }
        //释放ByteBuf
        buf.release();
    }
}
