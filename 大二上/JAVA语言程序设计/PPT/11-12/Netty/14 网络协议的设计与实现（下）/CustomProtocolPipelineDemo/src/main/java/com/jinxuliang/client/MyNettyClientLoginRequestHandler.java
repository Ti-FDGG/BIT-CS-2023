package com.jinxuliang.client;

import com.jinxuliang.codec.PacketCodec;
import com.jinxuliang.protocol.LoginRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;

//此Handler负责发送登录请求
public class MyNettyClientLoginRequestHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //检查是否已经登录成功？
        var loginResult = ctx.channel().attr(MyNettyClient.clientKey).get();
        if (loginResult == null) {
            //如果没有登录，则创建登录对象
            LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
            loginRequestPacket.setUserId(1000);
            //用户名为testUser，登录成功，为admin，登录会失败
            //loginRequestPacket.setUserName("testUser");
            loginRequestPacket.setUserName("admin");
            loginRequestPacket.setPassword("123456");
            var byteBuf = PacketCodec.encode(loginRequestPacket, null);
            //发送登录请求
            ctx.writeAndFlush(loginRequestPacket);
            //释放ByteBuf
            ReferenceCountUtil.release(byteBuf);
        } else {
            System.out.println("已发出过登录请求,登录结果为：" + loginResult);
        }
    }
}
