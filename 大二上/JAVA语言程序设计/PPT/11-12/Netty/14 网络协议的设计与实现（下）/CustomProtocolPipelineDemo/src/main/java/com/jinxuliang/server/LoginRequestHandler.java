package com.jinxuliang.server;

import com.jinxuliang.protocol.LoginRequestPacket;
import com.jinxuliang.protocol.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

//此Handler接收承载了用户登录信息的LoginRequestPacket，校验用户名与密码，然后，生成响应对象
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg)  {
        System.out.println("LoginRequestHandler收到:" + msg);
        //生成响应对象
        var response = new LoginResponsePacket();
        if (msg.getUserName().equals("testUser") && msg.getPassword().equals("123456")) {
            response.setIsSuccess(true);
            response.setMessage("LoginRequestHandler:登录成功！");
        } else {
            response.setIsSuccess(false);
            response.setMessage("LoginRequestHandler:登录失败，请检查用户名与密码是否正确。");
        }
        System.out.println("LoginRequestHandler生成响应对象：" + response);
        //转发给下一个Handler——PacketEncoder，发送给客户端
        ctx.writeAndFlush(response);
    }
}
