package com.jinxuliang;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;


public class MyNettyServer {
    private final int port = 9000;

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap boot = new ServerBootstrap();

            boot.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyInBoundHandler());

            ChannelFuture future = boot.bind(port).sync().addListener(new GenericFutureListener<Future<? super Void>>() {
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
