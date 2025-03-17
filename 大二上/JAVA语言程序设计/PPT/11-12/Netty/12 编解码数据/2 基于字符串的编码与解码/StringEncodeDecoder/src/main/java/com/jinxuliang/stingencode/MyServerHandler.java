package com.jinxuliang.stingencode;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

public class MyServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有客户端连接：" + ctx.channel().remoteAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("收到数据：" + msg + "。发送回执……");
        var sendBackMsg = "你发来的数据我已经收到".getBytes(StandardCharsets.UTF_8);
        //这里发送的消息，将会被StringEncoder编码为String，再发送出去
        ctx.writeAndFlush(Unpooled.wrappedBuffer(sendBackMsg));
        System.out.println("回执已经发送。");
    }
}
