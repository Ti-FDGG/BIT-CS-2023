package com.jinxuliang.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.StandardCharsets;

@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        String clientMessage = in.toString(CharsetUtil.UTF_8);
        System.out.println("服务器收到：" + clientMessage);
        if (clientMessage.equals("exit")) {
            System.out.println("断开连接：" + ctx.channel().remoteAddress());
            ctx.writeAndFlush(Unpooled.copiedBuffer(clientMessage, CharsetUtil.UTF_8));
            ctx.close();
        } else {
            String returnMessage = "[鹦鹉学舌服务器]" + clientMessage;
            ctx.writeAndFlush(Unpooled.wrappedBuffer(
                    returnMessage.getBytes(StandardCharsets.UTF_8)));
        }
        //释放掉ByteRef
        ReferenceCountUtil.release(msg);
    }
}
