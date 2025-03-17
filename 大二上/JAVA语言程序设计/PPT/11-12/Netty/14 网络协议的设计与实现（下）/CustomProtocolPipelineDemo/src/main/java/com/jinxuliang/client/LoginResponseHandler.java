package com.jinxuliang.client;

import com.jinxuliang.protocol.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

//当服务端传回响应之后，此处理程序接收响应，
//并依据收到的响应数据，将登录结果保存到通道的Attribute集合中
public class LoginResponseHandler extends
        SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                LoginResponsePacket msg) {
        System.out.println("LoginResponseHandler收到数据：" + msg);
        var info = "";
        if (msg.getIsSuccess()) {
            info = "success";
        } else {
            info = "fail";
        }
        ctx.channel().attr(MyNettyClient.clientKey).set(info);
        System.out.println("LoginResponseHandler设置登录结果：" + info);
    }
}
