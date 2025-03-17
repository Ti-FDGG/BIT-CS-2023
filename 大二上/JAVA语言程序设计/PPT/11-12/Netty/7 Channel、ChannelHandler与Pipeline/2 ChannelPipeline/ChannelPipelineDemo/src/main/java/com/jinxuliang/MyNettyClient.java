package com.jinxuliang;

import com.jinxuliang.handlers.MySimpleClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class MyNettyClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new MySimpleClientHandler());
        var connectFuture = bootstrap.connect("127.0.0.1", 9000)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println("连接成功");
                    } else {
                        System.out.println("连接失败");
                    }
                });
        connectFuture.channel().closeFuture().sync();
    }
}
