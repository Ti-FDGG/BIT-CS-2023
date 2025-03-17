package com.jinxuliang.variablelength;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class MyClientSendPrefixMessageHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ":客户端发送数据");
        //将一个字符串写入到ByteBuf中
        ByteBuf buf = ctx.alloc().buffer();
        writeMessage(buf, "白日依山尽");
        writeMessage(buf, "黄河入海流");
        writeMessage(buf,"欲穷千里目");
        writeMessage(buf,"更上一层楼");
        //将数据发送到服务端
        ctx.writeAndFlush(buf);
    }

    private static void writeMessage(ByteBuf buf, String message) {
        var messageData = message.getBytes(StandardCharsets.UTF_8);
        System.out.println("长度" + messageData.length);
        buf.writeInt(messageData.length);
        buf.writeBytes(messageData);
    }
}
