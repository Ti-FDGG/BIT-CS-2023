package com.jinxuliang.pingpong.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;

public class MyClientHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ByteBuf buf = (ByteBuf) o;
        System.out.println("收到服务端响应："+buf.toString(StandardCharsets.UTF_8));
    }
}
