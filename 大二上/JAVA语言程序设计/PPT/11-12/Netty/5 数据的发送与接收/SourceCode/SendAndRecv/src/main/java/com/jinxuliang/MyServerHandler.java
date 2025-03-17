package com.jinxuliang;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有客户端连接："+ctx.channel().remoteAddress());
        var welcomeMessage="欢迎你访问本服务器！".getBytes(StandardCharsets.UTF_8);
        ctx.writeAndFlush(Unpooled.wrappedBuffer(welcomeMessage));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("收到数据："+buf.toString(StandardCharsets.UTF_8));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开连接："+ctx.channel().remoteAddress());
    }
}
