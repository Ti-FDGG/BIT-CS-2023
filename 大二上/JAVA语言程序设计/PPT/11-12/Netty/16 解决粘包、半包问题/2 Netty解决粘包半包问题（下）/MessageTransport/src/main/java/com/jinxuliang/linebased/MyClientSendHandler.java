package com.jinxuliang.linebased;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

public class MyClientSendHandler extends ChannelInboundHandlerAdapter {
    private ChannelHandlerContext ctx = null;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;

        for (int i = 0; i < 300; i++) {
            sendMessage("打油诗一首");
            sendMessage("管它哪根葱与蒜，");
            sendMessage("拿着自已常开涮。");
            sendMessage("平凡日子开心过，");
            sendMessage("赚得笑声一串串。");
        }

    }

    private void sendMessage(String msg) {
        System.out.println("发送消息：" + msg);
        //用“\n”作为消息分隔符
        msg = msg + "\n";
        //将一个字符串写入到ByteBuf中
        ByteBuf buf = ctx.alloc().buffer();
        var messageData = msg.getBytes(StandardCharsets.UTF_8);
        buf.writeBytes(messageData);
        //将数据发送到服务端
        ctx.writeAndFlush(buf);
    }
}
