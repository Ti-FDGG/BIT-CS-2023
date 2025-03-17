package com.jinxuliang.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.io.IOException;

public class ChatClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private ChannelHandlerContext ctx;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        String serverMessage = msg.toString(CharsetUtil.UTF_8);
        System.out.println("\n<<< " + serverMessage);
        System.out.print(">>>");
    }

    //供外界调用，将一条消息发给服务器
    public void sendMessage(String msg) {
        //将消息发给服务器
        ctx.writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
    }

    //供外界调用，关闭与服务器的连接
    public void close() {
        ctx.close();
    }

}
