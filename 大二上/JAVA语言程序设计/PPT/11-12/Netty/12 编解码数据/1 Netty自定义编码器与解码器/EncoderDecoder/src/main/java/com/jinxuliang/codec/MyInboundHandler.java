package com.jinxuliang.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MyInboundHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        var info = "MyInboundHandler收到的数据：" + msg;
        System.out.println(info);
        var classInfo = "数据类型为：" + msg.getClass().getSimpleName();
        System.out.println(classInfo);
        //这里假设进行业务处理，比如汇总计算订单金额之类，得到结果为1000
        //将结果发回给客户端
        System.out.println("MyInboundHandler业务处理结束，向管线中写入数据:1000");
        ctx.writeAndFlush(1000);
    }
}
