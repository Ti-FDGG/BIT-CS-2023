package com.jinxuliang.pingpong.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyNettyClient {
    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new ClientHeartBeatHandler());
                        ch.pipeline().addLast(new MyClientHandler());
                    }
                });
        bootstrap.connect("127.0.0.1", 9000).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
            } else  {
                System.err.println("连接失败");
            }
        });
    }
}
