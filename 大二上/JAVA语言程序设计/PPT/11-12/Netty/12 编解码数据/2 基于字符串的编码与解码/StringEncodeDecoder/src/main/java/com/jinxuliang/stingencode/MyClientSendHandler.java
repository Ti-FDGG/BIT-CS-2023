package com.jinxuliang.stingencode;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class MyClientSendHandler extends SimpleChannelInboundHandler<String> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date()+":客户端发送数据");
        var messageData="你好，Netty Server".getBytes(StandardCharsets.UTF_8);
        //将数据发送到服务端
        ctx.writeAndFlush(Unpooled.wrappedBuffer(messageData));
    }
    //由于前一个入栈Handler已经将数据转换为了字符串，所以这里，直接就可以得到一个字符串格式的消息
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("收到数据："+msg);
        //收到数据之后，关闭连接
        ctx.close();
    }
}
