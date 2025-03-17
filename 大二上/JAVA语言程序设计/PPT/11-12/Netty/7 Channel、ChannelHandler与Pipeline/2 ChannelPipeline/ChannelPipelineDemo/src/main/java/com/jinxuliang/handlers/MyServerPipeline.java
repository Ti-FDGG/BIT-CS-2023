package com.jinxuliang.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.nio.charset.StandardCharsets;

public class MyServerPipeline {
    public static class SimpleInHandlerA extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            String info = buf.toString(StandardCharsets.UTF_8);
            System.out.println("SimpleInHandlerA.channelRead():" + info);
            ((ByteBuf) msg).resetReaderIndex();
            //如果注释掉它，则后继Handler将收不到消息
            //super.channelRead(ctx, msg);
            //手工恢复
            ctx.fireChannelRead(msg);
        }
    }


    public static class SimpleInHandlerB extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            String info = buf.toString(StandardCharsets.UTF_8);
            System.out.println("SimpleInHandlerB.channelRead():" + info);
            ((ByteBuf) msg).resetReaderIndex();
            super.channelRead(ctx, msg);
        }
    }

    public static class SimpleInHandlerC extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            String info = buf.toString(StandardCharsets.UTF_8);
            System.out.println("SimpleInHandlerC.channelRead():" + info);
            super.channelRead(ctx, msg);
        }
    }

    public static class SimpleInHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            String info = "收到信息," + buf.toString(StandardCharsets.UTF_8);
            System.out.println("SimpleInHandler.channelRead():" + info);
            String returnInfo = info + ",发送回执。";
            ctx.write(Unpooled.copiedBuffer(returnInfo.getBytes()));
        }
    }


    public static class SimpleOutHandlerA extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            String info = "前一个发来:“" + buf.toString(StandardCharsets.UTF_8) + "”";
            String nextInfo = "SimpleOutHandlerA：{" + info + "}";
            System.out.println("\nSimpleOutHandlerA.write()-->\n\t" + nextInfo);
            ByteBuf outBuf = Unpooled.wrappedBuffer(nextInfo.getBytes());
            //write()方法会导致消息向后一个出站Handler传送
            ctx.write(outBuf, promise);
            ctx.flush();
        }
    }

    public static class SimpleOutHandlerB extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            String info = "前一个发来:“" + buf.toString(StandardCharsets.UTF_8) + "”";
            String nextInfo = "SimpleOutHandlerB：[" + info + "]";
            System.out.println("\nSimpleOutHandlerB.write()-->\n\t" + nextInfo);
            ByteBuf outBuf = Unpooled.wrappedBuffer(nextInfo.getBytes());
            ctx.write(outBuf, promise);
        }
    }

    public static class SimpleOutHandlerC extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            String info = "前一个发来“" + buf.toString(StandardCharsets.UTF_8) + "”";
            String nextInfo = "SimpleOutHandlerC：(" + info + ")";
            System.out.println("\nSimpleOutHandlerC.write()-->\n\t" + nextInfo);
            ByteBuf outBuf = Unpooled.wrappedBuffer(nextInfo.getBytes());
            ctx.write(outBuf, promise);
        }
    }
}
