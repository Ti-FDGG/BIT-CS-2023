package com.jinxuliang;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.Date;

public class MyNettyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        var message = LocalTime.now() + ":你好，Netty Server!";
        var messageData = message.getBytes(StandardCharsets.UTF_8);
        //基于字节数组，构建一个ByteBuf对象
        ByteBuf buf = Unpooled.wrappedBuffer(messageData);
        //将数据发送到服务端
        ctx.writeAndFlush(buf);
        //关闭通道
        ctx.close();
        System.out.println("数据已经发送，通道被关闭。\n" + message);
    }
}
