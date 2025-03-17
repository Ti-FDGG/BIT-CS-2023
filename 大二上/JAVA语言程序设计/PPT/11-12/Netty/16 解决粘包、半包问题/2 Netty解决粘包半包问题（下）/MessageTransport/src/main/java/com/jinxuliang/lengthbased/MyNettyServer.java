package com.jinxuliang.lengthbased;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Date;

public class MyNettyServer {
    private int port = 9000;

    public void run() throws InterruptedException {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            ServerBootstrap boot = new ServerBootstrap();
            boot.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(
                                    102400,
                                    0,
                                    2,
                                    0,
                                    2));
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new MyServerHandler());
                            //注意，以下两个Handler是放到开头的
                            socketChannel.pipeline().addFirst(new StringEncoder());
                            socketChannel.pipeline().addFirst(new LengthFieldPrepender(2));
                        }
                    });

            ChannelFuture f = boot.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println(port + "端口绑定成功.");
                    } else {
                        System.out.println(port + "端口绑定成功失败");
                    }
                }
            });

        } finally {

        }
    }

    public static void main(String[] args) throws InterruptedException {
        new MyNettyServer().run();
    }
}
