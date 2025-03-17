package com.jinxuliang.fixedlength;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有客户端连接："+ctx.channel().remoteAddress());
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       ByteBuf buf = (ByteBuf) msg;
       //一个int类型占4个字节
        System.out.println("收到数字："+buf.readInt());
    }
}
