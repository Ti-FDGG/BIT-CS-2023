package com.jinxuliang;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Date;

public class MyNettyServer {
    private int port = 9000;

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        AttributeKey<String> severInfo = AttributeKey.newInstance("ServerInfo");
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.attr(severInfo, "Server启动于：" + new Date());
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new MyServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = serverBootstrap.bind(port).sync().addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println(port + "端口绑定成功.");
                    } else {
                        System.out.println(port + "端口绑定成功失败");
                    }
                }
            });
            //如果Channel关闭，这里等待其完成
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            bossGroup.shutdownGracefully().addListener((f) -> {
                System.out.println("bossGroup关闭");
            });
            workerGroup.shutdownGracefully().addListener(f -> {
                System.out.println("workerGroup关闭");
            });
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new MyNettyServer().run();
    }
}
