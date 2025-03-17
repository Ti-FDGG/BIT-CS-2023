package com.jinxuliang.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.StandardCharsets;
import java.time.LocalTime;

public class MySimpleClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String info = LocalTime.now() + " Hello!";
        ByteBuf byteBuf = Unpooled.wrappedBuffer(info.getBytes());
        ctx.writeAndFlush(byteBuf);
        System.out.println("发消息给Server:" + info);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("收到Server传来的消息：" + byteBuf.toString(StandardCharsets.UTF_8));
    }
}
