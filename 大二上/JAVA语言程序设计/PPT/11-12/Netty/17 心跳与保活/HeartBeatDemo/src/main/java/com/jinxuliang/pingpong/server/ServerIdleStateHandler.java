package com.jinxuliang.pingpong.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class ServerIdleStateHandler extends IdleStateHandler {
    //15秒必须有心跳数据包发来
    private static final int READER_IDLE_TIME = 15;

    public ServerIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(READER_IDLE_TIME + "秒内未收到心跳包，关闭连接");
        ctx.channel().close().addListener(future -> {
            if (future.isSuccess() && !ctx.channel().isActive()) {
                System.out.println("客户端通道成功关闭");
            } else {
                System.out.println("客户端通道关闭失败");
            }
        });
    }
}
