package com.jinxuliang.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class ChatServerHandler extends ChannelInboundHandlerAdapter {
    //保存所有在线用户
    static Set<Channel> channelList = new HashSet<Channel>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //广播新客户端上线消息
        channelList.forEach(channel -> {
            String msg = "[客户端]" + ctx.channel().remoteAddress() + "上线了";
            ByteBuf buf = Unpooled.wrappedBuffer(msg.getBytes());
            channel.writeAndFlush(buf);
        });
        //新用户加入Channel集合
        channelList.add(ctx.channel());
        System.out.println("有客户端连接：" + ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //广播新客户端上线消息
        channelList.forEach(channel -> {
            if (channel != ctx.channel()) {
                String msg = "【客户端】" + ctx.channel().remoteAddress() + "下线。";
                ByteBuf buf = Unpooled.wrappedBuffer(msg.getBytes());
                channel.writeAndFlush(buf);
            }
        });
        channelList.remove(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)  {
        String message = "【客户端】" + ctx.channel().remoteAddress() + "：" +
                ((ByteBuf) msg).toString(StandardCharsets.UTF_8);
        System.out.println(message);
        //向聊天室的其他用户，广播消息
        channelList.forEach(channel -> {
            if (channel != ctx.channel()) {
                ByteBuf buf = Unpooled.wrappedBuffer(message.getBytes());
                channel.writeAndFlush(buf);
            }
        });
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)  {
        System.out.println(cause.getMessage());
    }
}
