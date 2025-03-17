package com.jinxuliang;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class MyClientSendHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ":客户端发送数据");
        var messageData = "你好，Netty Server".getBytes(StandardCharsets.UTF_8);
        //将数据发送到服务端
        ctx.writeAndFlush(Unpooled.wrappedBuffer(messageData));
    }
}
