package com.jinxuliang.simple;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("有客户端连接："+ctx.channel().remoteAddress());
    }

    int readTimeout = 0;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            System.out.println("触发事件：" + event.state());
            if (event.state() == IdleState.READER_IDLE) {
                readTimeout++;
            }
            if (readTimeout == 3) {
                System.out.println("超时超过3次，断开连接");
                ctx.close();
            }
        }
    }
}
