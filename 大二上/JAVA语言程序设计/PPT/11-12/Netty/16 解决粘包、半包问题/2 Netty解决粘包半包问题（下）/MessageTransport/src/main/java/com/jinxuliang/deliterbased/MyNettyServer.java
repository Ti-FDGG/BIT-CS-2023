package com.jinxuliang.deliterbased;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class MyNettyServer {
    private int port=9000;

    public void run() throws InterruptedException {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        AttributeKey<String> severInfo=AttributeKey.newInstance("ServerInfo");
        try {
            ServerBootstrap boot = new ServerBootstrap();
            boot.group(boosGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,
                                    Unpooled.copiedBuffer(MyNettyClient.SEPARATOR.getBytes(StandardCharsets.UTF_8))));
                            socketChannel.pipeline().addLast(new MyServerHandler());
                        }
                    });
            ChannelFuture f = boot.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if(future.isSuccess()){
                        System.out.println(port+"端口绑定成功.");
                    }else {
                        System.out.println(port+"端口绑定成功失败");
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
