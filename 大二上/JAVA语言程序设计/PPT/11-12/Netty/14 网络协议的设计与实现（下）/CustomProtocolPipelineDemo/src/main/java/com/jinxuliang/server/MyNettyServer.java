package com.jinxuliang.server;

import com.jinxuliang.handler.PacketDecoder;
import com.jinxuliang.handler.PacketEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class MyNettyServer {

    private static final int PORT = 9000;

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        //编码器应该放在管线开头
                        ch.pipeline().addFirst(new PacketEncoder());
                    }
                });
        var bindResult = serverBootstrap.bind(PORT).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("端口[" + PORT + "]绑定成功!");
            } else {
                System.err.println("端口[" + PORT + "]绑定失败!");
            }
        });
        bindResult.channel().closeFuture().sync();
        boosGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
