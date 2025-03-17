package com.jinxuliang.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ChatServer {

    public static final int SERVER_PORT = 9000;

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch)  {
                            ch.pipeline().addLast(new ChatServerHandler());
                        }
                    });
            var bindResult = serverBootstrap.bind(SERVER_PORT).addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("端口[" + SERVER_PORT + "]绑定成功!");
                } else {
                    System.err.println("端口[" + SERVER_PORT + "]绑定失败!");
                }
            });
            bindResult.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
