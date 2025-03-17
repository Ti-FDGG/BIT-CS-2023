package com.jinxuliang;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class MyClientSendHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date()+":客户端发送数据");
        //将一个字符串写入到ByteBuf中
        ByteBuf buf = ctx.alloc().buffer();
        var messageData="你好，Netty Server!".getBytes(StandardCharsets.UTF_8);
        buf.writeBytes(messageData);
        //将数据发送到服务端
        ctx.writeAndFlush(buf);
        //信息发送完毕，立即关闭通道
        ctx.close();
    }
}
