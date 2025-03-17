package com.jinxuliang.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import java.io.IOException;

//注意SimpleChannelInboundHandler会自动释放ByteBuf
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    //接收服务端传回的消息
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        String serverMessage = msg.toString(CharsetUtil.UTF_8);
        if (serverMessage.equals("exit")) {
            ctx.close();
            System.out.println("程序退出");
        } else {
            System.out.println(serverMessage);
        }
        //收到一条消息之后，再次等待用户输入
        handleUserInput(ctx);
    }
    //当成功连接上服务器时，让用户输入消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        handleUserInput(ctx);
    }
    //接收用户输入
    private static void handleUserInput(ChannelHandlerContext ctx) {
        try {
            String userInput = InputHelper.getUserInput("请输入要发送给服务器的消息:");
            //将消息发给服务器
            ctx.writeAndFlush(Unpooled.copiedBuffer(userInput, CharsetUtil.UTF_8));
            if (userInput.equals("exit")) {
                System.out.println("关闭与服务器的连接，退出客户端……");
                ctx.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            ctx.close();
        }
    }
}
