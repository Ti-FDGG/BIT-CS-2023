package com.jinxuliang.pingpong.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.time.LocalTime;

public class ServerPingPongHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有客户端连接：" + ctx.channel().remoteAddress());
    }

    private static final byte[] pong = "pong".getBytes(StandardCharsets.UTF_8);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf bytebuf = (ByteBuf) msg;
        String clientSendMessage = bytebuf.toString(StandardCharsets.UTF_8);
        System.out.println("收到客户端消息：" + clientSendMessage);
        //如果客户端发来的是ping消息
        if (clientSendMessage.equals("ping")) {
            System.out.println(LocalTime.now() + ":服务端发送pong消息。");
            ByteBuf response = Unpooled.wrappedBuffer(pong);
            ctx.writeAndFlush(response);
        }
    }
}
