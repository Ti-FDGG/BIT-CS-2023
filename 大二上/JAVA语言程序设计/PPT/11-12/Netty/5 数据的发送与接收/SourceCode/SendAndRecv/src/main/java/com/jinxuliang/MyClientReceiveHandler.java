package com.jinxuliang;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

public class MyClientReceiveHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        var receivedMsg = ((ByteBuf) msg).toString(StandardCharsets.UTF_8);
        System.out.println("收到数据：" + receivedMsg);
        System.out.println("敲回车键退出……");
    }
}
