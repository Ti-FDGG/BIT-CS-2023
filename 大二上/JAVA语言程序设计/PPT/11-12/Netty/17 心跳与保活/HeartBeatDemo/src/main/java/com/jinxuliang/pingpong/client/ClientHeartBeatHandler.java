package com.jinxuliang.pingpong.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class ClientHeartBeatHandler extends ChannelInboundHandlerAdapter {
    //客户端每5秒发送一个心跳包
    private static final int HEARTBEAT_INTERVAL = 5;
    private static final byte[] HEARTBEAT_BYTE = "ping".getBytes(StandardCharsets.UTF_8);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx, HEARTBEAT_INTERVAL);
        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx,int interval) {
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                System.out.println(LocalTime.now()+":发送心跳包");
                ByteBuf ping = Unpooled.wrappedBuffer(HEARTBEAT_BYTE);
                ctx.writeAndFlush(ping);
                //继续发送心跳包，
                //scheduleSendHeartBeat(ctx,interval);
                //如果选择一个较长的时间间隔，比如20秒，服务端会由于在15秒内没收到客户端发来的心跳包
                //而关闭客户端通道
                scheduleSendHeartBeat(ctx,20);
            }else {
                System.out.println("通道已关闭");
            }
        }, interval, TimeUnit.SECONDS);
    }
}
