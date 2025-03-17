package com.jinxuliang.lengthbased;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientSendHandler extends SimpleChannelInboundHandler<String> {
    private ChannelHandlerContext ctx = null;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
    }

    private void sendMessage(String msg) {
        System.out.println("发送消息：" + msg);
        //将数据发送到服务端
        ctx.writeAndFlush(msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        System.out.println("收到消息：" + msg);
        for (int i = 1; i <= 100; i++) {
            sendMessage("----" + i + "----");
            sendMessage("打油诗一首");
            sendMessage("管它哪根葱与蒜，");
            sendMessage("拿着自已常开涮。");
            sendMessage("平凡日子开心过，");
            sendMessage("赚得笑声一串串。");
        }
    }
}
